package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.constant.RedisConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.SmsEnum;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.cache.RedisManager;
import cn.logicalthinking.common.util.ParamValidation;
import cn.logicalthinking.common.util.SmsCodeGen;
import cn.logicalthinking.common.util.TencentSMS;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @note 发送验证码
 * @auhtor 卢祖飞
 * @date 2018/12/26,18:53
 */
@Service
public class UpdatePwdSetPwdService extends AbstractService {

    @Resource
    private RedisManager redisManager;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;
        //获取手机号
        String teacherPhone = data.getParameter("teacherPhone");

        ParamValidation.isNotNull(teacherPhone,"手机号不能为空");

        //生成验证码
        String code = SmsCodeGen.getCode();
        int redisSmsCodeTimeout = RedisConstant.REDIS_SMS_CODE_TIMEOUT;

        int minute = (redisSmsCodeTimeout % 3600000) / 60000;

        String[] parm = {code,minute+""};

        SmsSingleSenderResult result = TencentSMS.sengSMS(teacherPhone, SmsEnum.FOR_LOGIN, parm);

        if("OK".equalsIgnoreCase(result.errMsg)){
            //把信息存入redis
            redisManager.set(RedisConstant.TEACHER_FORGET_SMS_CODE + teacherPhone, code, RedisConstant.REDIS_SMS_CODE_TIMEOUT);
            return Result.jsonMsg("200","短信发送成功!");
        }
            return Result.jsonMsg("500","短信发送失败!");
    }
}
