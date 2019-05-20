package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderCourseDao;
import cn.logicalthinking.common.entity.OrderCourse;
import cn.logicalthinking.common.exception.ValidataException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description  课程订单 根据id查询
 * @author 黄世杰
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaGetOrderCourseByIdService extends AbstractService{
	
	@Resource
	private OrderCourseDao orderCourseDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		String id=data.getParameter("id");
		if(StringUtils.isBlank(id))
			throw new ValidataException("id不能为空");



		OrderCourse orderCourse = orderCourseDao.selectOrderCourseById(Integer.parseInt(id));
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(), orderCourse);
	}

}
