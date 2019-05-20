package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @version 1.0
 * @date 2018-12-19
 */

public interface CourseTypeDao {

    //新增
    public int addCourseType(CourseType courseType) throws DaoException;

    //批量添加
    public int batchCourseType(List<CourseType> list) throws DaoException;

    //修改
    public int updateCourseType(CourseType courseType) throws DaoException;

    //删除
    public int removeCourseType(Map<String, Object> map) throws DaoException;

    //查询单个
    public CourseType selectCourseTypeById(Integer id) throws DaoException;

    //条件查询全部
    public List<CourseType> selectCourseTypeListAll(Map<String, Object> map) throws DaoException;

    //分页查询
    public PageInfo<CourseType> selectCourseTypeListByPage(Map<String, Object> map) throws DaoException;

    //查询总数
    public int selectCourseTypeCount(Map<String, Object> map) throws DaoException;


    /*********************************************自定义扩展sql***********************************************/

    //分页查询
    PageInfo<CourseType> selectCourseTypeListByPageWithCourseAndTeacher(Map<String, Object> map) throws DaoException;

    //新增
    public int addCourseTypes(CourseType courseType) throws DaoException;

    //通过课程id查询课程类别
    public List<CourseType> getType(Map<String, Object> map) throws DaoException;

    //通过课程id查询课程类别
    CourseType getTypeOne(String courseId) throws DaoException;

    //修改已报人数,提成
    int updateCourseTypeEnrolmentAndCommissionSafely(CourseType courseType) throws DaoException;




}