package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderCourseDao;
import cn.logicalthinking.common.entity.OrderCourse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description  课程订单 添加
 * @author 黄世杰
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaAddOrderCourseService extends AbstractService{
	
	@Resource
	private OrderCourseDao orderCourseDao;
	
	private IClientData data;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		OrderCourse orderCourse=(OrderCourse)data.getObject();
		
		orderCourseDao.addOrderCourse(orderCourse);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),CODE.CODE_200_ADD.getValue());
		
	}
	
	

}
