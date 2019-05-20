package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.FinanceStudent;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface FinanceStudentDao {

	//新增
	public int addFinanceStudent(FinanceStudent financeStudent) throws DaoException;
	
	//批量添加
	public int batchFinanceStudent(List<FinanceStudent> list) throws DaoException;

	//修改
	public int updateFinanceStudent(FinanceStudent financeStudent) throws DaoException;

	//删除
	public int removeFinanceStudent(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public FinanceStudent selectFinanceStudentById(String id) throws DaoException;

	//条件查询全部
	public List<FinanceStudent> selectFinanceStudentListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<FinanceStudent> selectFinanceStudentListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectFinanceStudentCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
	//查询单个
	public PageInfo<FinanceStudent> selectFinanceStudentByStudentId(Map<String, Object> map) throws DaoException;

	int removeFinanceStudentBysId(String studentId) throws DaoException;

}