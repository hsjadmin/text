package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.StudentHasCourseType;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface StudentHasCourseTypeDao {

	//新增
	public int addStudentHasCourseType(StudentHasCourseType studentHasCourseType) throws DaoException;
	
	//批量添加
	public int batchStudentHasCourseType(List<StudentHasCourseType> list) throws DaoException;

	//修改
	public int updateStudentHasCourseType(StudentHasCourseType studentHasCourseType) throws DaoException;

	//删除
	public int removeStudentHasCourseType(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public StudentHasCourseType selectStudentHasCourseTypeById(String id) throws DaoException;

	//条件查询全部
	public List<StudentHasCourseType> selectStudentHasCourseTypeListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<StudentHasCourseType> selectStudentHasCourseTypeListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectStudentHasCourseTypeCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/

	//分页查询 ,带老师信息
	public PageInfo<StudentHasCourseType> selectStudentHasCourseTypeListByPageWithTeacher(Map<String, Object> map) throws DaoException;

	int removeStudentHasCourseTypeBysId(String studentId) throws DaoException;

	int removeStudentHasCourseTypeById(Map<String, Object> map) throws DaoException;
}