package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.dao.MessageStudentDao;
import cn.logicalthinking.common.dao.OrderCourseDao;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.entity.MessageStudent;
import cn.logicalthinking.common.entity.OrderCourse;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @note 查询购买课程的学生
 * @auhtor 卢祖飞
 * @date 2018/12/26,15:04
 */
@Service
public class GetBuyCourseStudentService extends AbstractService {


    @Resource
    private StudentDao studentDao;

    @Resource
    private OrderCourseDao orderCourseDao;

    @Resource
    private CourseOutlineDao courseOutlineDao;

    @Resource
    private MessageStudentDao messageStudentDao;

    private IClientData data;


    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = new HashMap<String, Object>();
        //课程id
        String cId = data.getParameter("cId");
        //大纲id
        String coId = data.getParameter("coId");
        //大纲详情
        CourseOutline courseOutline = courseOutlineDao.selectCourseOutlineById(Integer.parseInt(coId));

        map.put("courseId",cId);

        List<OrderCourse> orderCourses = orderCourseDao.selectOrderCourseListAll(map);

        ParamValidation.isNotNull(orderCourses,"该课程无人报名");

        String studentId = "";

        for (OrderCourse orderCours : orderCourses) {
            studentId+=orderCours.getStudentId()+",";
        }

        if(studentId.lastIndexOf(",")!=-1){
            studentId=studentId.substring(0,studentId.lastIndexOf(","));
        }

        List<Student> students = studentDao.selectStudent(studentId);

        List<MessageStudent> messageStudents = new ArrayList<>();
        MessageStudent messageStudent = new MessageStudent();
        messageStudent.setCreateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
        messageStudent.setComment("您的《"+courseOutline.getTitle()+"》课程，将在"+getTime(courseOutline.getStartTime())+"后开课，建议您马上去课室开始上课哟~");
        messageStudent.setStatus(0);
        messageStudent.setTitle("上课提醒");
        for (Student student : students) {
            messageStudent.setStudentId(student.getId());
            messageStudents.add(messageStudent);
        }
        messageStudentDao.batchMessageStudent(messageStudents);

        return Result.jsonData(CODE.CODE_200_ADD.getKey(),students);
    }

    //计算时长
    public String  getTime(String startTime) throws Exception{
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;

        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd hh:mm");

        Date parse = time.parse(startTime);
        //long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff =  parse.getTime() - new Date().getTime() ;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        //long sec = diff % nd % nh % nm / ns;

         return day + "天" + hour + "小时" + min + "分钟";
    }
}
