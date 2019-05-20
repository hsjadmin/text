package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.FinanceDao;
import cn.logicalthinking.common.entity.Finance;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description  系统的财务管理 列表查询
 * @author 黄世杰
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaGetFinanceListService extends AbstractService{
	
	@Resource
	private FinanceDao financeDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"id" ,//主键
				"createTime" ,//创建时间
				"updateTime" ,//修改时间
				"studentName" ,//学生姓名
				"teacherName" ,//老师姓名
				"studentPay" ,//学生支付
				"platformIncome" ,//平台收益
				"teacherIncome" ,//老师收益
				"teacherBonusRatio" ,//老师提成比例
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<Finance> pageInfo = financeDao.selectFinanceListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
