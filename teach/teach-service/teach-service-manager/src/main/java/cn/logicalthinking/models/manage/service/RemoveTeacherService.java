package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.*;
import cn.logicalthinking.common.entity.*;
import cn.logicalthinking.common.exception.ValidataException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
/**
 * @Description  老师表 删除
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class RemoveTeacherService extends AbstractService{
	
	@Resource
	private TeacherDao teacherDao;

	@Resource
	private CourseDao courseDao;

	@Resource
	private CourseTypeDao courseTypeDao;

	@Resource
	private CourseOutlineDao courseOutlineDao;

	@Resource
	private CourseCommentDao courseCommentDao;

	@Resource
	private FinanceTeacherDao financeTeacherDao;

	@Resource
	private MessageTeacherDao messageTeacherDao;

	@Resource
	private QuestionCommentDao questionCommentDao;

	@Resource
	private StudentHasTeacherDao studentHasTeacherDao;

	@Resource
	private StudentHasCourseTypeDao studentHasCourseTypeDao;
	
	private IClientData data;
	
	 
	protected Result doWork(IClientData data) throws Exception {
		this.data=data;

		Map<String,Object> map=new HashMap<String,Object>();
		
		String ids=data.getParameter("ids");
		if(StringUtils.isBlank(ids))
			throw new ValidataException("ids不能为空");
        //删除课程相关
		removeCourseAll(ids);
        //删除老师明细
		financeTeacherDao.removeFinanceTeacgerBytId(ids);
		//删除老师消息
		messageTeacherDao.removeMessageTeacherBytId(ids);
        //删除疑难评论
        questionCommentDao.removeQuestionCommentBytId(ids);
        //删除学生关注老师
        studentHasTeacherDao.removeStudentHasTeacherBytId(ids);

		map.put("ids",getIds(ids));
		teacherDao.removeTeacher(map);
		
		return  Result.jsonMsg(CODE.CODE_200_DELETE.getKey(),CODE.CODE_200_DELETE.getValue());
		
	}

	private void removeCourseAll(String id){
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("teacherId",id);
        List<Course> courses = courseDao.selectCourseListAll(map1);
        List<CourseType> courseTypes = new ArrayList<CourseType>();
        HashSet<CourseOutline> courseOutlines = new HashSet<>();
        HashSet<CourseComment> courseComments = new HashSet<CourseComment>();
        HashSet<StudentHasCourseType> studentHasCourseTypes = new HashSet<>();
        for (Course cours : courses) {
            CourseType typeOne = courseTypeDao.getTypeOne(cours.getId().toString());
            courseTypes.add(typeOne);
            Map<String, Object> map = new HashMap<>();
            map.put("courseId",cours.getId());
            List<CourseComment> courseComments1 = courseCommentDao.selectCourseCommentListAll(map);
            courseComments.addAll(courseComments1);
            for (CourseType courseType : courseTypes) {
                HashMap<String, Object> map3 = new HashMap<>();
                map3.put("courseTypeId",courseType.getId());
                List<CourseOutline> courseOutlineList1 = courseOutlineDao.selectCourseOutlineListAll(map3);
                List<StudentHasCourseType> studentHasCourseTypes1 = studentHasCourseTypeDao.selectStudentHasCourseTypeListAll(map3);
                courseOutlines.addAll(courseOutlineList1);
                studentHasCourseTypes.addAll(studentHasCourseTypes1);
            }
        }
        //删除评论
//        removeCourseCommon(courseComments);
        //删除大纲
        removeCourseOutLine(courseOutlines);
        //删除学生关注课程
        removeStudentCourse(studentHasCourseTypes);
        //删除课程列表
        removeCourseType(courseTypes);
        //删除课程
        removeCourse(courses);
    }

    //删除课程
    private void removeCourse(List<Course> courses){
	    if (courses == null || courses.size() <= 0)
	        return;

        String ids = "";
        for (Course course : courses) {
            ids+="'"+course.getId()+"',";
        }
        if(ids.lastIndexOf(",")!=-1)
            ids=ids.substring(0,ids.lastIndexOf(","));

        Map<String, Object> map = new HashMap<>();
        map.put("ids",ids);
        courseDao.removeCourse(map);
    }

    //删除学生关注课程
    private void removeStudentCourse(HashSet<StudentHasCourseType> studentHasCourseTypes){
        if (studentHasCourseTypes == null || studentHasCourseTypes.size() <= 0)
            return;

        String ids = "";
        for (StudentHasCourseType studentCourse : studentHasCourseTypes) {
            ids+="'"+studentCourse.getCourseTypeId()+"',";
        }
        if(ids.lastIndexOf(",")!=-1)
            ids=ids.substring(0,ids.lastIndexOf(","));

        Map<String, Object> map = new HashMap<>();
        map.put("ids",ids);
        studentHasCourseTypeDao.removeStudentHasCourseTypeById(map);
    }


    //删除课程列表
    private void removeCourseType(List<CourseType> courseTypes){
	    if (courseTypes == null || courseTypes.size() <= 0)
	         return;
        String ids = "";
        for (CourseType courseType : courseTypes) {
            ids+="'"+courseType.getId()+"',";
        }
        if(ids.lastIndexOf(",")!=-1)
            ids=ids.substring(0,ids.lastIndexOf(","));

        Map<String, Object> map = new HashMap<>();
        map.put("ids",ids);

        courseTypeDao.removeCourseType(map);

    }


    //删除大纲
    private void removeCourseOutLine(HashSet<CourseOutline> courseOutlineList){
	    if (courseOutlineList == null || courseOutlineList.size() <= 0)
	            return;
        String ids = "";
        for (CourseOutline courseOutline : courseOutlineList) {
            ids+="'"+courseOutline.getId()+"',";
        }

        if(ids.lastIndexOf(",")!=-1)
            ids=ids.substring(0,ids.lastIndexOf(","));

        Map<String, Object> map = new HashMap<>();
        map.put("ids",ids);
        courseOutlineDao.removeCourseOutline(map);
    }

    /*//删除评论
    private void removeCourseCommon(HashSet<CourseComment> courseComments){
	    if (courseComments == null || courseComments.size() <= 0)
	            return;

	    String ids = "";
        for (CourseComment courseComment : courseComments) {
            ids+="'"+courseComment.getId()+"',";
        }

        if(ids.lastIndexOf(",")!=-1)
            ids=ids.substring(0,ids.lastIndexOf(","));

        Map<String, Object> map = new HashMap<>();
        map.put("ids",ids);
        courseCommentDao.removeCourseComment(map);
    }*/




	private static String getIds(String idsStr){
		String ids="";
		if(StringUtils.isBlank(idsStr))
			return ids;
		
		String[] arr=idsStr.split(",");
		for (int i = 0; i < arr.length; i++) {
			ids+="'"+arr[i]+"',";
		}
		if(StringUtils.isBlank(ids))
			return idsStr;
		if(ids.lastIndexOf(",")!=-1)
			ids=ids.substring(0,ids.lastIndexOf(","));
		return ids;
	}

}
