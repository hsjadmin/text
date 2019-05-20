package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.StudentHasTeacherDao;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.entity.StudentHasTeacher;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生关注老师表 添加
 * @date 2018-12-27
 */
@Service
public class StAddStudentHasTeacherService extends AbstractService {

    @Resource
    private StudentHasTeacherDao studentHasTeacherDao;

    private IClientData data;


    protected Result doWork(IClientData data) throws Exception {

        this.data = data;
        Student studentUser = data.getStudentUser();

        String teacherId = data.getParameter("teacherId");

        ParamValidation.isNotNull(teacherId, "老师id不能为空");

        HashMap<String, Object> map = new HashMap<>(2);
        map.put("teacherId", teacherId);
        map.put("studentId", studentUser.getId());
        // 取消关注
        if (studentHasTeacherDao.selectStudentHasTeacherCount(map) != 0) {

            map.put("studentIds", studentUser.getId());

            if (studentHasTeacherDao.removeStudentHasTeacher(map) == 0) {
                throw new BusinessException("取消关注失败");
            }

            return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), "取消关注成功");
        }
        StudentHasTeacher studentHasTeacher = new StudentHasTeacher();
        studentHasTeacher.setTeacherId(Integer.parseInt(teacherId));
        studentHasTeacher.setStudentId(studentUser.getId());

        if (studentHasTeacherDao.addStudentHasTeacher(studentHasTeacher) == 0) {
            throw new BusinessException("关注失败");
        }


        return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), "关注成功");

    }


}
