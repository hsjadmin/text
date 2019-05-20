package cn.logicalthinking.models.manage.service;


import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysRoleDao;
import cn.logicalthinking.common.entity.SysRole;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * @Description  角色表 列表查询
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class GetSysRoleListService extends AbstractService{
	
	@Resource
	private SysRoleDao sysRoleDao;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"roleName" ,//角色名
				"roleMark" ,//角色唯一标识
				"state" ,//角色状态(0有效1无效)
				"createTime" ,//创建时间
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<SysRole> pageInfo = sysRoleDao.selectSysRoleListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
