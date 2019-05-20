package cn.logicalthinking.common.aspect;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.dao.SysLogDao;
import cn.logicalthinking.common.entity.SysLog;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.HttpContextUtils;
import cn.logicalthinking.common.util.JwtUtils;
import cn.logicalthinking.common.util.Tools;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * 系统后台日志，切面处理类
 * @author 黄世杰
 * @version 1.0 
 * @date 2018-12-19
 *
 */
@Aspect
@Component
public class ServiceLogAspect{

    @Resource
    private SysLogDao sysLogDao;


    private HttpServletRequest request;

	@Pointcut("@annotation(cn.logicalthinking.common.base.annotation.ServiceLogAnn)")
	public void logPointCut() { 
	}
	
	//环绕通知  用于拦截Controller层记录用户的操作 
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();

        System.out.println("《《《《《《《《《《系统日志记录开始》》》》》》》》》》");

        saveSysLog(point);

        System.out.println("《《《《《《《《《《系统日志记录完毕》》》》》》》》》》");

        return  result;
	}
	
    @Before("logPointCut()")  
    public void doBefore(JoinPoint joinPoint) throws InterruptedException{
		 System.out.println("***************************执行doBefore*******************************");
    }
    
    @After("logPointCut()")
    public void after(JoinPoint joinPoint) throws InterruptedException{
		 System.out.println("***************************执行after*******************************");
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            ServiceLogAnn weblog = method.getAnnotation(ServiceLogAnn.class);
            request = HttpContextUtils.getHttpServletRequest();
            String token = request.getHeader("token");
            Claims claims = JwtUtils.parseJWT(token);
            if (claims == null) {
                return;
            }
            String subject = claims.getSubject();
            JSONObject jsonObject = JSON.parseObject(subject);

            SysLog log = new SysLog();
            log.setLogid(Tools.UUID());
            log.setUserId(claims.getId());

            log.setUsername(jsonObject.getString("userName"));
            log.setOperateName(weblog.operationName());
            log.setOperateUrl(weblog.url());
            log.setToken(token);
            System.out.println(token);
            log.setIp(HttpContextUtils.getIpAddress(request));
            log.setPort(String.valueOf(request.getServerPort()));
            log.setCreateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));
            log.setOpType(weblog.OPType());
            log.setType("2");
            sysLogDao.addSysLog(log);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
