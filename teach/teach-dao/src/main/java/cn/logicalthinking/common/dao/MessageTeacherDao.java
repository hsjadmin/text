package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.MessageTeacher;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface MessageTeacherDao {

	//新增
	public int addMessageTeacher(MessageTeacher messageTeacher) throws DaoException;
	
	//批量添加
	public int batchMessageTeacher(List<MessageTeacher> list) throws DaoException;

	//修改
	public int updateMessageTeacher(MessageTeacher messageTeacher) throws DaoException;

	//删除
	public int removeMessageTeacher(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public MessageTeacher selectMessageTeacherById(String id) throws DaoException;

	//条件查询全部
	public List<MessageTeacher> selectMessageTeacherListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<MessageTeacher> selectMessageTeacherListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectMessageTeacherCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/

	public PageInfo<MessageTeacher> selectMessageByTeacherId(Map<String, Object> map) throws DaoException;

	//删除
	public int removeMessageTeacherBytId(String teacherId) throws DaoException;

	public int updateReadStatus(Map<String, Object> map) throws DaoException;

}