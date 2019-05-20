package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.AddressDao;
import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.entity.Address;
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
public class AddressDaoImpl extends BaseDao implements AddressDao{

	public int addAddress(Address address) throws DaoException{
		return super.getSqlSession().insert("addAddress", address);
	}
	public int batchAddress(List<Address> list) throws DaoException{
		return super.getSqlSession().insert("batchAddress",list);
	}

	public int updateAddress(Address address) throws DaoException{
		return super.getSqlSession().update("updateAddress", address);
	}

	public int removeAddress(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeAddress", map);
	}

	public Address selectAddressById(Integer id) throws DaoException{
		return super.getSqlSession().selectOne("selectAddressById",id);
	}

	public List<Address> selectAddressListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectAddressListAll",map);
	}
	
	public PageInfo<Address> selectAddressListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<Address> selectList = super.getSqlSession().selectList("selectAddressListByPage", map);
        return new PageInfo<Address>(selectList);
	}

	public int selectAddressCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectAddressCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	public int setNotDefault(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().update("setNotDefault", map);
	}

	@Override
	public int removeAddressBysId(String studentId) throws DaoException {
		return super.getSqlSession().delete("removeAddressBysId",studentId);
	}
}