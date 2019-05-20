package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface StudentDao {

	//新增
	public int addStudent(Student student) throws DaoException;
	
	//批量添加
	public int batchStudent(List<Student> list) throws DaoException;

	//修改
	public int updateStudent(Student student) throws DaoException;

	//删除
	public int removeStudent(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public Student selectStudentById(Integer id) throws DaoException;

	//条件查询全部
	public List<Student> selectStudentListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<Student> selectStudentListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectStudentCount(Map<String, Object> map) throws DaoException;


	/*********************************************自定义扩展sql***********************************************/

	//根据openId查询用户信息
	public Student selectStudentByOpenId(String openId) throws DaoException;

	//查询购买视频学生
	public List<Student> selectStudent(String ids) throws DaoException;

	//修改余额
	int updateStudentBalance(Map<String, Object> map) throws DaoException;

	//修改学生余额
	public int updateStudentBalances(Student student);
}