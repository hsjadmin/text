package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.Student;
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
public class StudentDaoImpl extends BaseDao implements StudentDao{

	public int addStudent(Student student) throws DaoException{
		return super.getSqlSession().insert("addStudent", student);
	}
	public int batchStudent(List<Student> list) throws DaoException{
		return super.getSqlSession().insert("batchStudent",list);
	}

	public int updateStudent(Student student) throws DaoException{
		return super.getSqlSession().update("updateStudent", student);
	}

	public int removeStudent(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeStudent", map);
	}

	public Student selectStudentById(Integer id) throws DaoException{
		return super.getSqlSession().selectOne("selectStudentById",id);
	}

	public List<Student> selectStudentListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectStudentListAll",map);
	}
	
	public PageInfo<Student> selectStudentListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<Student> selectList = super.getSqlSession().selectList("selectStudentListByPage", map);
        return new PageInfo<Student>(selectList);
	}

	public int selectStudentCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectStudentCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/

	public Student selectStudentByOpenId(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectStudentByOpenId",id);
	}

	@Override
	public List<Student> selectStudent(String id) throws DaoException {
		return super.getSqlSession().selectList("selectStudent",id);
	}

	@Override
	public int updateStudentBalances(Student student) {
		return super.getSqlSession().update("updateStudentBalances",student);
	}

	public int updateStudentBalance(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().update("updateStudentBalance", map);
	}
}