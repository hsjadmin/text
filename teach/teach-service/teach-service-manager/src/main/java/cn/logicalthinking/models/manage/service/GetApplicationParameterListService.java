package cn.logicalthinking.models.manage.service;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import com.github.pagehelper.PageInfo;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;
/**
 * @Description  系统参数设置表 列表查询
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetApplicationParameterListService extends AbstractService{
	
	@Resource
	private ApplicationParameterDao applicationParameterDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"id" ,//主键
				"createTime" ,//创建时间
				"updateTime" ,//修改时间
				"name" ,//参数名
				"value" ,//参数值
				"remark" ,//参数说明
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<ApplicationParameter> pageInfo = applicationParameterDao.selectApplicationParameterListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
