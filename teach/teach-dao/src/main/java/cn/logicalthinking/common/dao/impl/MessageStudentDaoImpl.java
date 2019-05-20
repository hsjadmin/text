package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.MessageStudentDao;
import cn.logicalthinking.common.entity.MessageStudent;
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
public class MessageStudentDaoImpl extends BaseDao implements MessageStudentDao {

    public int addMessageStudent(MessageStudent messageStudent) throws DaoException {
        return super.getSqlSession().insert("addMessageStudent", messageStudent);
    }

    public int batchMessageStudent(List<MessageStudent> list) throws DaoException {
        return super.getSqlSession().insert("batchMessageStudent", list);
    }

    public int updateMessageStudent(MessageStudent messageStudent) throws DaoException {
        return super.getSqlSession().update("updateMessageStudent", messageStudent);
    }

    public int removeMessageStudent(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().delete("removeMessageStudent", map);
    }

    public MessageStudent selectMessageStudentById(Integer id) throws DaoException {
        return super.getSqlSession().selectOne("selectMessageStudentById", id);
    }

    public List<MessageStudent> selectMessageStudentListAll(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().selectList("selectMessageStudentListAll", map);
    }

    public PageInfo<MessageStudent> selectMessageStudentListByPage(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<MessageStudent> selectList = super.getSqlSession().selectList("selectMessageStudentListByPage", map);
        return new PageInfo<MessageStudent>(selectList);
    }

    public int selectMessageStudentCount(Map<String, Object> map) throws DaoException {
        Object obj = super.getSqlSession().selectOne("selectMessageStudentCount", map);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        return 0;
    }

    /*********************************************自定义扩展sql***********************************************/

    public int readAll(Integer studentId) throws DaoException {
        return super.getSqlSession().update("readAll", studentId);
    }

    public int removeAll(Integer studentId) throws DaoException {
        return super.getSqlSession().delete("removeAll", studentId);
    }
}