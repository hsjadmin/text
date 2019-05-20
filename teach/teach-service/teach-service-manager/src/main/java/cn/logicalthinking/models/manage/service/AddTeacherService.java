package cn.logicalthinking.models.manage.service;

import javax.annotation.Resource;

import cn.logicalthinking.common.util.DateUtil;
import org.springframework.stereotype.Service;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;

import java.util.Date;

/**
 * @Description  老师表 添加
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class AddTeacherService extends AbstractService{
	
	@Resource
	private TeacherDao teacherDao;
	
	private IClientData data;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Teacher teacher=(Teacher)data.getObject();

		teacher.setCreateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
		
		teacherDao.addTeacher(teacher);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),CODE.CODE_200_ADD.getValue());
		
	}
	
	

}
