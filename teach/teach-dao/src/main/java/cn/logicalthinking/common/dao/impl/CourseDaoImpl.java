package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @version 1.0
 * @date 2018-12-19
 */
@Component
public class CourseDaoImpl extends BaseDao implements CourseDao {

    public int addCourse(Course course) throws DaoException {
        return super.getSqlSession().insert("addCourse", course);
    }

    public int batchCourse(List<Course> list) throws DaoException {
        return super.getSqlSession().insert("batchCourse", list);
    }

    public int updateCourse(Course course) throws DaoException {
        return super.getSqlSession().update("updateCourse", course);
    }

    public int removeCourse(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().delete("removeCourse", map);
    }

    public Course selectCourseById(Integer id) throws DaoException {
        return super.getSqlSession().selectOne("selectCourseById", id);
    }

    public List<Course> selectCourseListAll(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().selectList("selectCourseListAll", map);
    }

    public PageInfo<Course> selectCourseListByPage(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<Course> selectList = super.getSqlSession().selectList("selectCourseListByPage", map);
        return new PageInfo<Course>(selectList);
    }

    public int selectCourseCount(Map<String, Object> map) throws DaoException {
        Object obj = super.getSqlSession().selectOne("selectCourseCount", map);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        return 0;
    }

    /*********************************************自定义扩展sql***********************************************/


    public Course selectCourseByIdWithCourseTypeAndCourseOutline(Integer id) throws DaoException {
        return super.getSqlSession().selectOne("selectCourseByIdWithCourseTypeAndCourseOutline", id);
    }

    @Override
    public PageInfo<Course> selectCourseListByTeacherId(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<Course> selectList = super.getSqlSession().selectList("selectCourseListByTeacherId", map);
        return new PageInfo<Course>(selectList);
    }

    @Override
    public Course selectCourseInfo(Integer id) throws DaoException {
        return super.getSqlSession().selectOne("selectCourseInfo",id);
    }

    @Override
    public int addCourses(Course course) throws DaoException {
        return super.getSqlSession().insert("addCourses", course);
    }

    public int removeCourse(Integer ids) throws DaoException {
        return super.getSqlSession().delete("removeCourse", ids);
    }


    public int updateCourseStatus(Integer teacherId) throws DaoException {
        return super.getSqlSession().update("updateCourseStatus", teacherId);
    }
}