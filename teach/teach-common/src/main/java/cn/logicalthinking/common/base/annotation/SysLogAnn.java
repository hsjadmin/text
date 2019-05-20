package cn.logicalthinking.common.base.annotation;

import java.lang.annotation.*;

/**
 * 系统权限日志注解
 * @author 黄世杰
 * @version 1.0 
 * @date 2018-12-19
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAnn {
	
	/** 层级<li>1：注解在Service层 <li>2：注解在Controller层 **/
	int level() default 1;

	/** 描述 **/
	String description() default "";
	 
	/** 要执行的具体操作比如：添加用户 **/  
	String operationName() default "";
}
