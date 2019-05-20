package cn.logicalthinking.models.manage.service;

import javax.annotation.Resource;

import cn.logicalthinking.common.dao.FinanceTeacherDao;
import cn.logicalthinking.common.entity.FinanceTeacher;
import cn.logicalthinking.common.util.ParamValidation;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.dao.TeacherDao;
import  cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;

import java.util.Map;

/**
 * @Description  查询老师收支信息
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetTeacherFinanceByIdService extends AbstractService{
	
	@Resource
	private FinanceTeacherDao financeTeacherDao;

	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;

		Map<String, Object> map = data.initMap();

		String id=data.getParameter("teacherId");
		if(StringUtils.isBlank(id))
			throw new ValidataException("id不能为空");
		map.put("teacherId",id);
		String type = data.getParameter("type");
		if(StringUtils.isNotBlank(type)){
			map.put("type",type);
		}
		String startTime = data.getParameter("startTime");
		if(StringUtils.isNotBlank(startTime)){
			map.put("startTime",startTime);
		}
		String endTime = data.getParameter("endTime");
		if(StringUtils.isNotBlank(endTime)){
			map.put("endTime",endTime);
		}
		PageInfo<FinanceTeacher> financeTeacherPageInfo = financeTeacherDao.selectFinanceTeacherbyTeacherId(map);

		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(), financeTeacherPageInfo.getList(),financeTeacherPageInfo.getTotal());
	}

}
