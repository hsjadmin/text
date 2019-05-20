package cn.logicalthinking.models.manage.service;


import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.entity.OrderQuestion;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description  解疑订单查询
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class GetDoubtsOrderService extends AbstractService{
	
	@Resource
	private OrderQuestionDao questionDao;


	protected Result doWork(IClientData data) throws Exception {
		Map<String,Object> map=data.initMap();
		String[] arr={
				"studentName",//学生姓名
				"teacherName",//老师姓名
				"status", //审核状态
				"price",//价格
				"grade"//年级
		};

		data.setConditionMap(map,arr);

		PageInfo<OrderQuestion> pageInfo=questionDao.selectOrderQuestionListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
		
	}
}
