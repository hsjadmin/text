package cn.logicalthinking.models.manage.service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import cn.logicalthinking.common.dao.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;
/**
 * @Description  学生表 删除
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class RemoveStudentService extends AbstractService{
	
	@Resource
	private StudentDao studentDao;

	@Resource
    private AddressDao addressDao;

	@Resource
    private CouponDao couponDao;

	@Resource
    private FinanceStudentDao financeStudentDao;

	@Resource
    private MessageStudentDao messageStudentDao;

	@Resource
    private OrderRechargeDao rechargeDao;

	@Resource
    private StudentHasCourseTypeDao studentHasCourseTypeDao;

	@Resource
    private StudentHasTeacherDao studentHasTeacherDao;
	
	private IClientData data;
	
	 
	protected Result doWork(IClientData data) throws Exception {
		this.data=data;
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		String ids=data.getParameter("ids");
		if(StringUtils.isBlank(ids))
			throw new ValidataException("ids不能为空");
        //删除学生地址
		addressDao.removeAddressBysId(ids);
        //删除学生优惠券
		couponDao.removeCouponBysId(ids);
        //删除学生账户明细
		financeStudentDao.removeFinanceStudentBysId(ids);
        //删除学生信息
        messageStudentDao.removeAll(Integer.parseInt(ids));
        //删除充值订单
        rechargeDao.removeOrderRechargeBysId(ids);
        //删除订阅课程
        studentHasCourseTypeDao.removeStudentHasCourseTypeBysId(ids);
        //删除学生关注老师
        studentHasTeacherDao.removeStudentHasTeacherBysId(ids);
		map.put("ids",getIds(ids));
		
		studentDao.removeStudent(map);
		
		return  Result.jsonMsg(CODE.CODE_200_DELETE.getKey(),CODE.CODE_200_DELETE.getValue());
		
	}
	
	private static String getIds(String idsStr){
		String ids="";
		if(StringUtils.isBlank(idsStr))
			return ids;
		
		String[] arr=idsStr.split(",");
		for (int i = 0; i < arr.length; i++) {
			ids+="'"+arr[i]+"',";
		}
		if(StringUtils.isBlank(ids))
			return idsStr;
		if(ids.lastIndexOf(",")!=-1)
			ids=ids.substring(0,ids.lastIndexOf(","));
		return ids;
	}

}
