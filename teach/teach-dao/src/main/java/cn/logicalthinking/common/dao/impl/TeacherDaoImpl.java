package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.LGS;
import cn.logicalthinking.common.entity.Level;
import cn.logicalthinking.common.entity.Teacher;
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
public class TeacherDaoImpl extends BaseDao implements TeacherDao {

    public int addTeacher(Teacher teacher) throws DaoException {
        return super.getSqlSession().insert("addTeacher", teacher);
    }

    public int batchTeacher(List<Teacher> list) throws DaoException {
        return super.getSqlSession().insert("batchTeacher", list);
    }

    public int updateTeacher(Teacher teacher) throws DaoException {
        return super.getSqlSession().update("updateTeacher", teacher);
    }

    public int removeTeacher(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().delete("removeTeacher", map);
    }

    public Teacher selectTeacherById(Integer id) throws DaoException {
        return super.getSqlSession().selectOne("selectTeacherById", id);
    }

    public List<Teacher> selectTeacherListAll(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().selectList("selectTeacherListAll", map);
    }

    public PageInfo<Teacher> selectTeacherListByPage(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<Teacher> selectList = super.getSqlSession().selectList("selectTeacherListByPage", map);
        return new PageInfo<Teacher>(selectList);
    }

    public int selectTeacherCount(Map<String, Object> map) throws DaoException {
        Object obj = super.getSqlSession().selectOne("selectTeacherCount", map);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        return 0;
    }

    /*********************************************自定义扩展sql***********************************************/
    @Override
    public Teacher isRegister(String phone) throws DaoException {
        return super.getSqlSession().selectOne("isRegister", phone);
    }

    @Override
    public Teacher getTeacherInfo(Integer id) throws DaoException {
        return super.getSqlSession().selectOne("getTeacherInfo", id);
    }

    @Override
    public Teacher getTeacherBalance(Integer id) throws DaoException {
        return super.getSqlSession().selectOne("getTeacherBalance", id);
    }

    @Override
    public int updateBalance(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().update("updateBalance", map);
    }

    @Override
    public PageInfo<Teacher> selectTeacherListByPageOrderByFully(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<Teacher> selectList = super.getSqlSession().selectList("selectTeacherListByPageOrderByFully", map);
        return new PageInfo<Teacher>(selectList);
    }

    @Override
    public PageInfo<Teacher> selectTeacherListByPageOrderByStar(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<Teacher> selectList = super.getSqlSession().selectList("selectTeacherListByPageOrderByStar", map);
        return new PageInfo<Teacher>(selectList);
    }

    @Override
    public List<LGS> selectLGS() throws DaoException {
        return super.getSqlSession().selectList("selectLGS");
    }

    @Override
    public int addBalance(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().update("addBalance",map);
    }

    @Override
    public List<Level> selectLevel() throws DaoException {
        return super.getSqlSession().selectList("selectLevel");
    }

    @Override
    public int updateBalanceSafeLy(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().update("updateBalanceSafeLy", map);
    }

    @Override
    public int updateOnlineStatusByPhone(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().update("updateOnlineStatusByPhone", map);
    }
}