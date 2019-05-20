package cn.logicalthinking.models.manage.service;



import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.entity.SysUser;

import com.github.pagehelper.PageInfo;
/**
 * @Description  用户表 列表查询
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class GetSysUserListService extends AbstractService{
	
	@Resource
	private SysUserDao sysUserDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"userId" ,//主键
				"userName" ,//登录名
				"trueName" ,//真实姓名
				"sex" ,//性别(0男1女3保密)
				"email" ,//电子邮箱
				"phone" ,//手机号
				"pwd" ,//密码
				"createTime" ,//创建时间
				"loginTime" ,//登录时间
				"lastLoginTime" ,//最后一次登录时间
				"state" ,//用户状态(0有效  1无效)
				"type" ,//用户类型(0前台用户1后台用户)
				"picturePath" ,//头像路径
				"address" ,//所在地
				"roleId",//角色id
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<SysUser> pageInfo = sysUserDao.selectSysUserRoleListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
