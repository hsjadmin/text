package cn.logicalthinking.models.manage.service.course;

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
 * @Description  老师表 列表查询
 * @author xhx
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaGetTeacherListService extends AbstractService{
	
	@Resource
	private TeacherDao teacherDao;
	
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		Map<String,Object> map=data.initMap();

		String[] conditionArr = new String[]{
				"name" ,//真实姓名
				"phone" ,//手机号
				"userName" ,//用户名
                "region" ,//省市区
				"address" ,//详细地址
				"subject" ,//学科
				"level" ,//学段
				"chargeSettings"
		};

        data.setConditionMap(map,conditionArr);

		map.put("examine", 2);

		PageInfo<Teacher> pageInfo = teacherDao.selectTeacherListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
