package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.MessageTeacherDao;
import cn.logicalthinking.common.entity.MessageTeacher;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@Component
public class MessageTeacherDaoImpl extends BaseDao implements MessageTeacherDao{

	public int addMessageTeacher(MessageTeacher messageTeacher) throws DaoException{
		return super.getSqlSession().insert("addMessageTeacher", messageTeacher);
	}
	public int batchMessageTeacher(List<MessageTeacher> list) throws DaoException{
		return super.getSqlSession().insert("batchMessageTeacher",list);
	}

	public int updateMessageTeacher(MessageTeacher messageTeacher) throws DaoException{
		return super.getSqlSession().update("updateMessageTeacher", messageTeacher);
	}

	public int removeMessageTeacher(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeMessageTeacher", map);
	}

	public MessageTeacher selectMessageTeacherById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectMessageTeacherById",id);
	}

	public List<MessageTeacher> selectMessageTeacherListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectMessageTeacherListAll",map);
	}
	
	public PageInfo<MessageTeacher> selectMessageTeacherListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<MessageTeacher> selectList = super.getSqlSession().selectList("selectMessageTeacherListByPage", map);
        return new PageInfo<MessageTeacher>(selectList);
	}

	public int selectMessageTeacherCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectMessageTeacherCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/

	@Override
	public PageInfo<MessageTeacher> selectMessageByTeacherId(Map<String, Object> map) throws DaoException {
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
		PageHelper.startPage(map);
		List<MessageTeacher> selectList = super.getSqlSession().selectList("selectMessageByTeacherId", map);
		return new PageInfo<MessageTeacher>(selectList);
	}

	@Override
	public int removeMessageTeacherBytId(String teacherId) throws DaoException {
		return super.getSqlSession().delete("removeMessageTeacherBytId",teacherId);
	}

	@Override
	public int updateReadStatus(Map<String, Object> map) throws DaoException {
		return super.getSqlSession().update("updateReadStatus", map);
	}
}