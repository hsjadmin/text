package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.AddressDao;
import cn.logicalthinking.common.entity.Address;
import cn.logicalthinking.common.entity.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @Description  学生的收货地址表 添加
 * @author xhx
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class StAddAddressService extends AbstractService{
	
	@Resource
	private AddressDao addressDao;
	
	private IClientData data;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Address address=(Address)data.getObject();

		Student studentUser = data.getStudentUser();
		address.setStudentId(studentUser.getId());

		addressDao.addAddress(address);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),CODE.CODE_200_ADD.getValue());
		
	}
	
	

}
