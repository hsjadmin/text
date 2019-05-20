package cn.logicalthinking.models.manage.service;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  系统参数设置表 修改
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class UpdateApplicationParameterService extends AbstractService{
	
	@Resource
	private ApplicationParameterDao applicationParameterDao;
	
	private IClientData data;
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		String str = data.getParameter("str");

		List<ApplicationParameter> getlist = getlist(str);

		for (ApplicationParameter parameter : getlist) {
			applicationParameterDao.updateApplicationParameterbyName(parameter);
		}
		return  Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
		
	}

	public List<ApplicationParameter> getlist(String str){
		List<ApplicationParameter> applicationParameter=new ArrayList<ApplicationParameter>();
		try {
			applicationParameter = JSON.parseArray(str, ApplicationParameter.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ValidataException("fileInfo解析失败");
		}
		return applicationParameter;
	}

}
