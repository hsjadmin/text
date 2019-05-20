package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.MessageStudent;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @version 1.0
 * @date 2018-12-19
 */

public interface MessageStudentDao {

    //新增
    public int addMessageStudent(MessageStudent messageStudent) throws DaoException;

    //批量添加
    public int batchMessageStudent(List<MessageStudent> list) throws DaoException;

    //修改
    public int updateMessageStudent(MessageStudent messageStudent) throws DaoException;

    //删除
    public int removeMessageStudent(Map<String, Object> map) throws DaoException;

    //查询单个
    public MessageStudent selectMessageStudentById(Integer id) throws DaoException;

    //条件查询全部
    public List<MessageStudent> selectMessageStudentListAll(Map<String, Object> map) throws DaoException;

    //分页查询
    public PageInfo<MessageStudent> selectMessageStudentListByPage(Map<String, Object> map) throws DaoException;

    //查询总数
    public int selectMessageStudentCount(Map<String, Object> map) throws DaoException;


    /*********************************************自定义扩展sql***********************************************/
    //全部已读
    public int readAll(Integer studentId) throws DaoException;

    //全部删除
    public int removeAll(Integer studentId) throws DaoException;
}