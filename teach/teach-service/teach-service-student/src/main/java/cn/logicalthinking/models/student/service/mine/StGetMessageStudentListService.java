package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MessageStudentDao;
import cn.logicalthinking.common.entity.MessageStudent;
import cn.logicalthinking.common.entity.Student;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * @Description  学生的消息 列表查询
 * @author xhx
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class StGetMessageStudentListService extends AbstractService{
	
	@Resource
	private MessageStudentDao messageStudentDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		Student studentUser = data.getStudentUser();

		map.put("studentId", studentUser.getId());

		PageInfo<MessageStudent> pageInfo = messageStudentDao.selectMessageStudentListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
