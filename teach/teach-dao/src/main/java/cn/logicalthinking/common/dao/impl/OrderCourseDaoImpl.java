package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.OrderCourseDao;
import cn.logicalthinking.common.entity.OrderCourse;
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
public class OrderCourseDaoImpl extends BaseDao implements OrderCourseDao {

    public int addOrderCourse(OrderCourse orderCourse) throws DaoException {
        return super.getSqlSession().insert("addOrderCourse", orderCourse);
    }

    public int batchOrderCourse(List<OrderCourse> list) throws DaoException {
        return super.getSqlSession().insert("batchOrderCourse", list);
    }

    public int updateOrderCourse(OrderCourse orderCourse) throws DaoException {
        return super.getSqlSession().update("updateOrderCourse", orderCourse);
    }

    public int removeOrderCourse(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().delete("removeOrderCourse", map);
    }

    public OrderCourse selectOrderCourseById(Integer id) throws DaoException {
        return super.getSqlSession().selectOne("selectOrderCourseById", id);
    }

    public List<OrderCourse> selectOrderCourseListAll(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().selectList("selectOrderCourseListAll", map);
    }

    public PageInfo<OrderCourse> selectOrderCourseListByPage(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<OrderCourse> selectList = super.getSqlSession().selectList("selectOrderCourseListByPage", map);
        return new PageInfo<OrderCourse>(selectList);
    }

    public int selectOrderCourseCount(Map<String, Object> map) throws DaoException {
        Object obj = super.getSqlSession().selectOne("selectOrderCourseCount", map);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        return 0;
    }

    /*********************************************自定义扩展sql***********************************************/

    public PageInfo<OrderCourse> selectOrderCourseListByPageWithCourseType(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<OrderCourse> selectList = super.getSqlSession().selectList("selectOrderCourseListByPageWithCourseType", map);
        return new PageInfo<OrderCourse>(selectList);
    }

    public PageInfo<OrderCourse> selectOrderCourseListByPageWithCourseTypeAndCourse(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<OrderCourse> selectList = super.getSqlSession().selectList("selectOrderCourseListByPageWithCourseTypeAndCourse", map);
        return new PageInfo<OrderCourse>(selectList);
    }
}