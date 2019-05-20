package cn.logicalthinking.models.student.controller;

import cn.logicalthinking.common.base.constant.YunXinConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.util.HttpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 【学生】基础数据
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/student/base")
@Api(tags = "【学生】基础数据")
public class StBaseController extends BaseController {

    @Resource(name = "stGetLGSService")
    private AbstractService stGetLGSService;

    /**
     * @throws Exception
     * @Description 查询LGS
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/lgs")
    @ApiOperation(value = "查询LGS", response = Result.class, httpMethod = "POST")
    public Result stGetLGSService() throws Exception {
        return stGetLGSService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 查询LGS
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/test")
    @ApiOperation(value = "test", response = Result.class, httpMethod = "POST")
    public Result test() throws Exception {
//        String mpId = WxProp4Student.MP_ID;
//        System.out.println(mpId);


        Map<String, Object> map = new HashMap<>();
        map.put(YunXinConstant.ACCID, "15170192080");
        map.put(YunXinConstant.ICON, "https://avatar.csdnimg.cn/7/9/B/1_wang135139.jpg");
        map.put(YunXinConstant.NAME, "小浣熊");
        String yunXinRegister = HttpRequest.yunXinRegister(YunXinConstant.REGISTER_URL, map);
        System.out.println(yunXinRegister);

//        OrderRechargePayCallback orderRechargePayCallback = SpringContextHolder.WEB_APP_CONTEXT.getBean(OrderRechargePayCallback.class);
//        OrderRechargePayCallback orderRechargePayCallback2 = SpringContextHolder.WEB_APP_CONTEXT.getBean(OrderRechargePayCallback.class);
//
//        Class<?> targetClass = AopUtils.getTargetClass(orderRechargePayCallback2);
//        Callback simpleNameCallback = (Callback) SpringContextHolder.WEB_APP_CONTEXT.getBean(targetClass);
//
        Object[] res = {
//                String.valueOf(orderRechargePayCallback),
//                String.valueOf(orderRechargePayCallback2),
//                String.valueOf(simpleNameCallback),
//                AopUtils.getTargetClass(orderRechargePayCallback).getSimpleName(),
//                AopUtils.getTargetClass(orderRechargePayCallback2).getSimpleName(),
                yunXinRegister,
        };


        return Result.jsonData("200", res);
    }

}
