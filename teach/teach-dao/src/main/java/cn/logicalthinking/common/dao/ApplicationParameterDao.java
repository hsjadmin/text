package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface ApplicationParameterDao {

	//新增
	public int addApplicationParameter(ApplicationParameter applicationParameter) throws DaoException;
	
	//批量添加
	public int batchApplicationParameter(List<ApplicationParameter> list) throws DaoException;

	//修改
	public int updateApplicationParameter(ApplicationParameter applicationParameter) throws DaoException;

	//删除
	public int removeApplicationParameter(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public ApplicationParameter selectApplicationParameterById(String id) throws DaoException;

	//条件查询全部
	public List<ApplicationParameter> selectApplicationParameterListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<ApplicationParameter> selectApplicationParameterListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectApplicationParameterCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/

	//修改参数
	public int updateApplicationParameterbyName(ApplicationParameter applicationParameter) throws DaoException;

	//条件查询全部
	public ApplicationParameter selectApplicationParameterByName(Map<String, Object> map) throws DaoException;

	//按名字查询
	public ApplicationParameter selectApplicationParameterByName(String name) throws DaoException;
}