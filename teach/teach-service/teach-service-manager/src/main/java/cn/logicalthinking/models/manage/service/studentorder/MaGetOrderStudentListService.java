package cn.logicalthinking.models.manage.service.studentorder;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderStudentDao;
import cn.logicalthinking.common.entity.OrderStudent;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * @Description  学生订单 列表查询
 * @author xhx
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaGetOrderStudentListService extends AbstractService{
	
	@Resource
	private OrderStudentDao orderStudentDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"id" ,//主键
				"createTime" ,//创建时间
				"updateTime" ,//修改时间s
				"studentName" ,//学生形象
				"gender" ,//性别（0男 1女  2保密）
				"address" ,//地址
				"phone" ,//
				"userName" ,//用户名
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<OrderStudent> pageInfo = orderStudentDao.selectOrderStudentListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
