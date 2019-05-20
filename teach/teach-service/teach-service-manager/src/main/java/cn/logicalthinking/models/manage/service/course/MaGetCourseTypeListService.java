package cn.logicalthinking.models.manage.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.entity.CourseType;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * @Description  课程类型表，即大班课小班课一对一等课程 列表查询
 * @author xhx
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaGetCourseTypeListService extends AbstractService{
	
	@Resource
	private CourseTypeDao courseTypeDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"level" ,//级别
				"grade" ,//科目
				"subject" ,//科目
				"courseType" ,//课程类型（0 一对一，1小班课，2大班课）
				"duration" ,//课程时长
				"periods" ,//授课课时
				"courseTime" ,//上课时间
				"teacherName" ,//老师姓名
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<CourseType> pageInfo = courseTypeDao.selectCourseTypeListByPageWithCourseAndTeacher(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
