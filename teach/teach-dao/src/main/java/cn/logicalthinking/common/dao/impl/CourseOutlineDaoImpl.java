package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.entity.CourseOutline;
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
public class CourseOutlineDaoImpl extends BaseDao implements CourseOutlineDao {

    public int addCourseOutline(CourseOutline courseOutline) throws DaoException {
        return super.getSqlSession().insert("addCourseOutline", courseOutline);
    }

    public int batchCourseOutline(List<CourseOutline> list) throws DaoException {
        return super.getSqlSession().insert("batchCourseOutline", list);
    }

    public int updateCourseOutline(CourseOutline courseOutline) throws DaoException {
        return super.getSqlSession().update("updateCourseOutline", courseOutline);
    }

    public int removeCourseOutline(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().delete("removeCourseOutline", map);
    }

    public CourseOutline selectCourseOutlineById(Integer id) throws DaoException {
        return super.getSqlSession().selectOne("selectCourseOutlineById", id);
    }

    public List<CourseOutline> selectCourseOutlineListAll(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().selectList("selectCourseOutlineListAll", map);
    }

    public PageInfo<CourseOutline> selectCourseOutlineListByPage(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<CourseOutline> selectList = super.getSqlSession().selectList("selectCourseOutlineListByPage", map);
        return new PageInfo<CourseOutline>(selectList);
    }

    public int selectCourseOutlineCount(Map<String, Object> map) throws DaoException {
        Object obj = super.getSqlSession().selectOne("selectCourseOutlineCount", map);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        return 0;
    }

    /*********************************************自定义扩展sql***********************************************/

    @Override
    public PageInfo<CourseOutline> selectCourseOutlineBycId(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<CourseOutline> selectList = super.getSqlSession().selectList("selectCourseOutlineBycId", map);
        return new PageInfo<CourseOutline>(selectList);
    }

    @Override
    public List<CourseOutline> getOutLine(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().selectList("getOutLine", map);
    }

    @Override
    public int updateUrl(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().update("updateUrl", map);
    }

    @Override
    public int deleteCourseOutlineByctId(String ctId) throws DaoException {
        return super.getSqlSession().delete("deleteCourseOutlineByctId", ctId);
    }

    @Override
    public CourseOutline selectCourseOutlineBychannelNo(String channelNo) throws DaoException {
        return super.getSqlSession().selectOne("selectCourseOutlineBychannelNo",channelNo);
    }
}