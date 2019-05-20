package cn.logicalthinking.common.base.service;

import cn.logicalthinking.common.base.entity.Result;

/**
 * @Description 业务抽象类
 * @author 黄世杰 
 * @2018-12-19
 * @version  1.0
 */
public abstract class AbstractService {
	
	public Result execute(IClientData data) throws Exception{
		
		Result result=new Result();
		
		before(data);
		
		interceptor(data);
		
		result=doWork(data);
		
		after(data);
		
		return result;
	}
	
	/**
	 * @Description 执行之前执行
	 * @throws Exception
	 */
	protected void before(IClientData data){}
	
	/**
	 * @Description 业务拦截器
	 * @throws Exception
	 */
	protected void interceptor(IClientData data){}
	
	/**
	 * @Description 主要业务实现
	 * @return
	 */
	protected abstract Result doWork(IClientData data) throws Exception;
	
	/**
	 * @Description 执行之后执行
	 * @throws Exception
	 */
	protected void after(IClientData data){}
}
