package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.StudentHasTeacher;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface StudentHasTeacherDao {

	//新增
	public int addStudentHasTeacher(StudentHasTeacher studentHasTeacher) throws DaoException;
	
	//批量添加
	public int batchStudentHasTeacher(List<StudentHasTeacher> list) throws DaoException;

	//修改
	public int updateStudentHasTeacher(StudentHasTeacher studentHasTeacher) throws DaoException;

	//删除
	public int removeStudentHasTeacher(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public StudentHasTeacher selectStudentHasTeacherById(String id) throws DaoException;

	//条件查询全部
	public List<StudentHasTeacher> selectStudentHasTeacherListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<StudentHasTeacher> selectStudentHasTeacherListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectStudentHasTeacherCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/

	//分页查询
	public PageInfo<StudentHasTeacher> selectStudentHasTeacherListByPageWithTeacher(Map<String, Object> map) throws DaoException;

	int removeStudentHasTeacherBysId(String studentId) throws DaoException;

	int removeStudentHasTeacherBytId(String teacherId) throws DaoException;
}