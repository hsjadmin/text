package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.StudentHasTeacherDao;
import cn.logicalthinking.common.entity.StudentHasTeacher;
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
public class StudentHasTeacherDaoImpl extends BaseDao implements StudentHasTeacherDao{

	public int addStudentHasTeacher(StudentHasTeacher studentHasTeacher) throws DaoException{
		return super.getSqlSession().insert("addStudentHasTeacher", studentHasTeacher);
	}
	public int batchStudentHasTeacher(List<StudentHasTeacher> list) throws DaoException{
		return super.getSqlSession().insert("batchStudentHasTeacher",list);
	}

	public int updateStudentHasTeacher(StudentHasTeacher studentHasTeacher) throws DaoException{
		return super.getSqlSession().update("updateStudentHasTeacher", studentHasTeacher);
	}

	public int removeStudentHasTeacher(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeStudentHasTeacher", map);
	}

	public StudentHasTeacher selectStudentHasTeacherById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectStudentHasTeacherById",id);
	}

	public List<StudentHasTeacher> selectStudentHasTeacherListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectStudentHasTeacherListAll",map);
	}
	
	public PageInfo<StudentHasTeacher> selectStudentHasTeacherListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<StudentHasTeacher> selectList = super.getSqlSession().selectList("selectStudentHasTeacherListByPage", map);
        return new PageInfo<StudentHasTeacher>(selectList);
	}

	public int selectStudentHasTeacherCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectStudentHasTeacherCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/

	public PageInfo<StudentHasTeacher> selectStudentHasTeacherListByPageWithTeacher(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
		PageHelper.startPage(map);
		List<StudentHasTeacher> selectList = super.getSqlSession().selectList("selectStudentHasTeacherListByPageWithTeacher", map);
		return new PageInfo<StudentHasTeacher>(selectList);
	}

	@Override
	public int removeStudentHasTeacherBysId(String studentId) throws DaoException {
		return super.getSqlSession().delete("removeStudentHasTeacherBysId",studentId);
	}

	@Override
	public int removeStudentHasTeacherBytId(String teacherId) throws DaoException {
		return super.getSqlSession().delete("removeStudentHasTeacherBytId",teacherId);
	}
}