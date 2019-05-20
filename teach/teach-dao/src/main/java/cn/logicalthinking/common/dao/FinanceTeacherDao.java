package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.FinanceTeacher;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface FinanceTeacherDao {

	//新增
	public int addFinanceTeacher(FinanceTeacher financeTeacher) throws DaoException;
	
	//批量添加
	public int batchFinanceTeacher(List<FinanceTeacher> list) throws DaoException;

	//修改
	public int updateFinanceTeacher(FinanceTeacher financeTeacher) throws DaoException;

	//删除
	public int removeFinanceTeacher(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public FinanceTeacher selectFinanceTeacherById(String id) throws DaoException;

	//条件查询全部
	public List<FinanceTeacher> selectFinanceTeacherListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<FinanceTeacher> selectFinanceTeacherListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectFinanceTeacherCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/

	//分页查询
	public PageInfo<FinanceTeacher> selectFinanceTeacherbyTeacherId(Map<String, Object> map) throws DaoException;

	//根据老师id查询明细
	public PageInfo<FinanceTeacher> getFinanceTeacherbyTeacherId(Map<String, Object> map) throws DaoException;

	int removeFinanceTeacgerBytId(String teacherId) throws DaoException;
}