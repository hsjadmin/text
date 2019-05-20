package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * @Description  查询约课老师列表
 * @author xhx
 * @date   2018-12-27
 * @version  1.0
 */
@Service
public class StGetTeacherListService extends AbstractService{
	
	@Resource
	private TeacherDao teacherDao;
	
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		Map<String,Object> map=data.initMap();

		String condition[] = {
				"level",
				"grade",
				"subject",
				"level",
				"name",
		};

		data.setConditionMap(map, condition);

		PageInfo<Teacher> pageInfo = teacherDao.selectTeacherListByPageOrderByFully(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
