package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description  审核管理--审核老师
 * @author 黄世杰
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class ExamineUserInfoService extends AbstractService{

	@Resource
	private TeacherDao teacherDao;

	private IClientData data;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;

		String id=data.getParameter("id");
		String type=data.getParameter("type");
		String reason=data.getParameter("reason");
		if(StringUtils.isBlank(id))
			throw  new ValidataException("id不能为空");
		if(StringUtils.isBlank(type))
			throw  new ValidataException("type不能为空");
		Teacher teacher=teacherDao.selectTeacherById(Integer.parseInt(id));
		if(teacher==null)
			throw  new ValidataException("该用户不存在");

		if("0".equals(type)){//审核
			updateTeacher(teacher);
		}else if("1".equals(type)) {//不通过
			updateTeacher(teacher,reason);
		}else{
			throw  new ValidataException("类型不匹配");
		}
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),"审核成功");
		
	}

	private void updateTeacher(Teacher teacher){
		teacher.setExamine("2");
		teacher.setUpdateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
		teacherDao.updateTeacher(teacher);
	}

	private void updateTeacher(Teacher teacher,String reason){
		teacher.setExamine("3");
		teacher.setUpdateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
		teacher.setReason(reason);
		teacherDao.updateTeacher(teacher);
	}

}
