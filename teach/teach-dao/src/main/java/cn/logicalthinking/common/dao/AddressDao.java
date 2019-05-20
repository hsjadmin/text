package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.Address;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface AddressDao {

	//新增
	public int addAddress(Address address) throws DaoException;
	
	//批量添加
	public int batchAddress(List<Address> list) throws DaoException;

	//修改
	public int updateAddress(Address address) throws DaoException;

	//删除
	public int removeAddress(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public Address selectAddressById(Integer id) throws DaoException;

	//条件查询全部
	public List<Address> selectAddressListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<Address> selectAddressListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectAddressCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
	// 指定学生的全部地址为非默认地址
	int setNotDefault(Map<String, Object> map) throws DaoException;

	int removeAddressBysId(String studentId) throws DaoException;
}