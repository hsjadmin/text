package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.FinanceStudentDao;
import cn.logicalthinking.common.entity.FinanceStudent;
import cn.logicalthinking.common.exception.ValidataException;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description  查询学生收支记录
 * @author 黄世杰
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetStudentfinanceByIdService extends AbstractService{
	

	@Resource
	private FinanceStudentDao financeStudentDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;

		Map<String, Object> map = data.initMap();
		String money = data.getParameter("money");
		String createTime = data.getParameter("createTime");
		String startTime = data.getParameter("startTime");
		String endTime = data.getParameter("endTime");

		String studentId=data.getParameter("studentId");
		if(StringUtils.isBlank(studentId))
			throw new ValidataException("id不能为空");

		map.put("studentId",studentId);
		if(StringUtils.isNotBlank(money)){
			map.put("money",money);
		}
		if(StringUtils.isNotBlank(createTime)){
			map.put("createTime",createTime);
		}

		PageInfo<FinanceStudent> financeStudentPageInfo = financeStudentDao.selectFinanceStudentByStudentId(map);

		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(), financeStudentPageInfo.getList(),financeStudentPageInfo.getTotal());
	}

}
