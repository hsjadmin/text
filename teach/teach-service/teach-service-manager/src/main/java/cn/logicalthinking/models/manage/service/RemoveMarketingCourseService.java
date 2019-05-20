package cn.logicalthinking.models.manage.service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.dao.MarketingCourseDao;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;
/**
 * @Description  营销素材-课程 删除
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class RemoveMarketingCourseService extends AbstractService{
	
	@Resource
	private MarketingCourseDao marketingCourseDao;
	
	private IClientData data;
	
	 
	protected Result doWork(IClientData data) throws Exception {
		this.data=data;
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		String ids=data.getParameter("ids");
		if(StringUtils.isBlank(ids))
			throw new ValidataException("ids不能为空");
		
		map.put("ids",getIds(ids));	
		
		marketingCourseDao.removeMarketingCourse(map);
		
		return  Result.jsonMsg(CODE.CODE_200_DELETE.getKey(),CODE.CODE_200_DELETE.getValue());
		
	}
	
	private static String getIds(String idsStr){
		String ids="";
		if(StringUtils.isBlank(idsStr))
			return ids;
		
		String[] arr=idsStr.split(",");
		for (int i = 0; i < arr.length; i++) {
			ids+="'"+arr[i]+"',";
		}
		if(StringUtils.isBlank(ids))
			return idsStr;
		if(ids.lastIndexOf(",")!=-1)
			ids=ids.substring(0,ids.lastIndexOf(","));
		return ids;
	}

}
