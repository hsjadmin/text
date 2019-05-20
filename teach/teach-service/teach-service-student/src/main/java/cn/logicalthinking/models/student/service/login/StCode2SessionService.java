package cn.logicalthinking.models.student.service.login;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.pay.WxProp4Student;
import cn.logicalthinking.common.util.HttpsRequest;
import cn.logicalthinking.common.util.ParamValidation;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生表 登录凭证校验
 * @date 2018-12-19
 */
@Service
public class StCode2SessionService extends AbstractService {

    private static Logger logger = Logger.getLogger(StCode2SessionService.class);

    @Resource
    private StudentDao studentDao;

    protected Result doWork(IClientData data) throws Exception {

        String code = data.getParameter("code");

        ParamValidation.isNotNull(code, "授权code不能为空");

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        url = url.replace("APPID", WxProp4Student.MP_ID)
                .replace("SECRET", WxProp4Student.MP_SECRET)
                .replace("JSCODE", code);
        JSONObject jsonObject = HttpsRequest.httpRequest(url, "GET", null);

        logger.info(jsonObject);

        // 成功
        if (StringUtils.isBlank(jsonObject.getString("errcode"))) {
            String openid = jsonObject.getString("openid");
            Student student = studentDao.selectStudentByOpenId(openid);

            jsonObject.put("isRegisted", student != null);
            if (student != null) {
                jsonObject.put("phone", student.getPhone());
                jsonObject.put("student", student);
            }
            return Result.jsonData("200", jsonObject);
        }

        logger.error(jsonObject);
        return Result.jsonData("500", jsonObject.getString("errmsg"));

    }


}
