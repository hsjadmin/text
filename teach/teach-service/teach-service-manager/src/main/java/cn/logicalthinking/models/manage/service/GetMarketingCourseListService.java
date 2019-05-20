package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MarketingCourseDao;
import cn.logicalthinking.common.entity.MarketingCourse;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * @Description  营销素材-课程 列表查询
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetMarketingCourseListService extends AbstractService{
	
	@Resource
	private MarketingCourseDao marketingCourseDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"name" ,//课程名
				"type" ,//课程类型，直接存语文数学等
				"status" ,//状态（0上架  1下架）
				"createTime"//发布时间
		};
		
		data.setConditionMap(map,conditionArr);

		PageInfo<MarketingCourse> pageInfo = marketingCourseDao.selectMarketingCourseListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
