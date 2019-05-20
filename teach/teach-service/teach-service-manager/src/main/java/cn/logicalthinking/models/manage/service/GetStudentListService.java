package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.Student;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * @Description  学生表 列表查询
 * @author xhx
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetStudentListService extends AbstractService{
	
	@Resource
	private StudentDao studentDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"name" ,//真实姓名
				"phone" ,//手机号
				"region",//省市区
				"userName" ,//用户名
				"address" ,//详细地址
				"grade" ,//年级
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<Student> pageInfo = studentDao.selectStudentListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
