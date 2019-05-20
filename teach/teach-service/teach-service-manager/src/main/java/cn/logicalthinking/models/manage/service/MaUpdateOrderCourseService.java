package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderCourseDao;
import cn.logicalthinking.common.entity.OrderCourse;
import cn.logicalthinking.common.exception.ValidataException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description  课程订单 修改
 * @author 黄世杰
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaUpdateOrderCourseService extends AbstractService{
	
	@Resource
	private OrderCourseDao orderCourseDao;
	
	private IClientData data;
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		OrderCourse orderCourse=(OrderCourse)data.getObject();
		
		if(orderCourse.getId() == null)
			throw new ValidataException("id不能为空");
		
		orderCourseDao.updateOrderCourse(orderCourse);
		
		return  Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
		
	}

}
