package cn.logicalthinking.models.manage.service.studentorder;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderStudentDao;
import cn.logicalthinking.common.exception.ValidataException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
/**
 * @Description  学生订单 删除
 * @author xhx
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaRemoveOrderStudentService extends AbstractService{
	
	@Resource
	private OrderStudentDao orderStudentDao;
	
	private IClientData data;
	
	 
	protected Result doWork(IClientData data) throws Exception {
		this.data=data;
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		String ids=data.getParameter("ids");
		if(StringUtils.isBlank(ids))
			throw new ValidataException("ids不能为空");
		
		map.put("ids",getIds(ids));	
		
		orderStudentDao.removeOrderStudent(map);
		
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
