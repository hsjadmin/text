package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.StudentHasCourseTypeDao;
import cn.logicalthinking.common.entity.StudentHasCourseType;
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
public class StudentHasCourseTypeDaoImpl extends BaseDao implements StudentHasCourseTypeDao{

	public int addStudentHasCourseType(StudentHasCourseType studentHasCourseType) throws DaoException{
		return super.getSqlSession().insert("addStudentHasCourseType", studentHasCourseType);
	}
	public int batchStudentHasCourseType(List<StudentHasCourseType> list) throws DaoException{
		return super.getSqlSession().insert("batchStudentHasCourseType",list);
	}

	public int updateStudentHasCourseType(StudentHasCourseType studentHasCourseType) throws DaoException{
		return super.getSqlSession().update("updateStudentHasCourseType", studentHasCourseType);
	}

	public int removeStudentHasCourseType(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeStudentHasCourseType", map);
	}

	public StudentHasCourseType selectStudentHasCourseTypeById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectStudentHasCourseTypeById",id);
	}

	public List<StudentHasCourseType> selectStudentHasCourseTypeListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectStudentHasCourseTypeListAll",map);
	}
	
	public PageInfo<StudentHasCourseType> selectStudentHasCourseTypeListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<StudentHasCourseType> selectList = super.getSqlSession().selectList("selectStudentHasCourseTypeListByPage", map);
        return new PageInfo<StudentHasCourseType>(selectList);
	}

	public int selectStudentHasCourseTypeCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectStudentHasCourseTypeCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/

	public PageInfo<StudentHasCourseType> selectStudentHasCourseTypeListByPageWithTeacher(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
		PageHelper.startPage(map);
		List<StudentHasCourseType> selectList = super.getSqlSession().selectList("selectStudentHasCourseTypeListByPageWithTeacher", map);
		return new PageInfo<StudentHasCourseType>(selectList);
	}

	@Override
	public int removeStudentHasCourseTypeBysId(String studentId) throws DaoException {
		return super.getSqlSession().delete("removeStudentHasCourseTypeBysId",studentId);
	}

	@Override
	public int removeStudentHasCourseTypeById(Map<String, Object> map) throws DaoException {
		return super.getSqlSession().delete("removeStudentHasCourseTypeById",map);
	}
}