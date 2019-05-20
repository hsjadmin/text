package cn.logicalthinking.common.base.annotation;

import java.lang.annotation.*;

/**
 * 业务日志注解
 * @author 黄世杰
 * @version 1.0 
 * @date 2018-12-19
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLogAnn {
	
	/** 层级<li>1：注解在Service层 <li>2：注解在Controller层 **/
	int level() default 1;

	/** 操纵类型（新增、删除、确认、取消确认、修改、审核、取消审核等） **/  
	String OPType() default "";
	 
	/** 要执行的具体操作比如：添加用户 **/  
	String operationName() default "";
	
	/** 要url：admin/addUser**/  
	String url() default "";
}
