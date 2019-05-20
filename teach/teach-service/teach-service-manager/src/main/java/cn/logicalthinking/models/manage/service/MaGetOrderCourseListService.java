package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderCourseDao;
import cn.logicalthinking.common.entity.OrderCourse;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description  课程订单 列表查询
 * @author 黄世杰
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaGetOrderCourseListService extends AbstractService{
	
	@Resource
	private OrderCourseDao orderCourseDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"id" ,//主键
				"createTime" ,//创建时间
				"updateTime" ,//修改时间
				"studentName" ,//学生姓名
				"gender" ,//性别（0男 1女）
				"address" ,//地址
				"phone" ,//手机号
				"orig" ,//原价，缩写
				"discount" ,//优惠价
				"periods" ,//课时
				"duration" ,//课程时长，分钟
				"courseTime" ,//上课时间
				"teacherName" ,//老师姓名
				"isCoupon" ,//是否使用优惠券
				"couponPrice" ,//优惠券金额
				"teacherBonus" ,//教师提成
				"studentId" ,//学生id
				"teacherId" ,//老师id
				"courseId" ,//课程id
				"courseTypeId" ,//课程类型id
				"grade" ,//学生年级
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<OrderCourse> pageInfo = orderCourseDao.selectOrderCourseListByPageWithCourseType(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
