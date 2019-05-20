package cn.logicalthinking.models.manage.service;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.dao.TeacherDao;
import com.github.pagehelper.PageInfo;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;
/**
 * @Description  老师表 列表查询
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetTeacherListService extends AbstractService{
	
	@Resource
	private TeacherDao teacherDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"name" ,//真实姓名
				"phone" ,//手机号
				"region" ,//省市区
				"address" ,//详细地址
				"userName" ,//用户名
				"chargeSettings" ,//超时收费设置（元/分钟）
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<Teacher> pageInfo = teacherDao.selectTeacherListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
