package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @version 1.0
 * @date 2018-12-19
 */

public interface CourseDao {

    //新增
    public int addCourse(Course course) throws DaoException;

    //批量添加
    public int batchCourse(List<Course> list) throws DaoException;

    //修改
    public int updateCourse(Course course) throws DaoException;

    //删除
    public int removeCourse(Map<String, Object> map) throws DaoException;

    //查询单个
    public Course selectCourseById(Integer id) throws DaoException;

    //条件查询全部
    public List<Course> selectCourseListAll(Map<String, Object> map) throws DaoException;

    //分页查询
    public PageInfo<Course> selectCourseListByPage(Map<String, Object> map) throws DaoException;

    //查询总数
    public int selectCourseCount(Map<String, Object> map) throws DaoException;


    /*********************************************自定义扩展sql***********************************************/
    /**
     * 根据id查询，带课程类型，课程大纲
     * @param id
     * @return
     * @throws DaoException
     */
    Course selectCourseByIdWithCourseTypeAndCourseOutline(Integer id) throws DaoException;

    //分页查询
    public PageInfo<Course> selectCourseListByTeacherId(Map<String, Object> map) throws DaoException;

    //根据课程id查询课程信息
    public Course selectCourseInfo(Integer id) throws DaoException;

    //添加课程
    public int addCourses(Course course) throws DaoException;

    //删除
    public int removeCourse(@Param("ids") Integer ids) throws DaoException;
    //删除
    public int updateCourseStatus(@Param("teacherId") Integer teacherId) throws DaoException;
}