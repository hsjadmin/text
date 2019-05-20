package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.Finance;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface FinanceDao {

	//新增
	public int addFinance(Finance finance) throws DaoException;
	
	//批量添加
	public int batchFinance(List<Finance> list) throws DaoException;

	//修改
	public int updateFinance(Finance finance) throws DaoException;

	//删除
	public int removeFinance(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public Finance selectFinanceById(String id) throws DaoException;

	//条件查询全部
	public List<Finance> selectFinanceListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<Finance> selectFinanceListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectFinanceCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
}