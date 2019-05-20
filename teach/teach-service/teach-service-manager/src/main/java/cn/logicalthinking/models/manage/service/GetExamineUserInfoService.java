package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CouponDao;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Coupon;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description  审核管理--用户信息列表查询
 * @author 黄世杰
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetExamineUserInfoService extends AbstractService{

	@Resource
	private TeacherDao teacherDao;

	private IClientData data;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;

		//获取老师信息
		Map<String,Object> map=data.initMap();
		String[] arr={
			"grade",//年级
				"subject"//科目
		};
		map.put("examine","1");
		map.put("status","0");
		data.setConditionMap(map,arr);

		PageInfo<Teacher> pageInfo= teacherDao.selectTeacherListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
		
	}
	
	

}
