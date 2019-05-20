package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description  学生表 添加
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class AddStudentService extends AbstractService{
	
	@Resource
	private StudentDao studentDao;
	
	private IClientData data;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Student student=(Student)data.getObject();

		List<Student> students = studentDao.selectStudentListAll(null);
		for (Student student1 : students) {
			if(student.getPhone().equals(student1.getPhone())){
				throw new ValidataException("手机号已存在");
			}
		}
		student.setStatus(0);
		student.setCreateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
		studentDao.addStudent(student);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),CODE.CODE_200_ADD.getValue());
		
	}
	
	

}
