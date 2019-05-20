package cn.logicalthinking.models.student.service.login;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @Description  学生表 添加
 * @author xhx
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class StAddStudentService extends AbstractService{
	
	@Resource
	private StudentDao studentDao;
	
	private IClientData data;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Student student=(Student)data.getObject();
		
		studentDao.addStudent(student);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),CODE.CODE_200_ADD.getValue());
		
	}
	
	

}
