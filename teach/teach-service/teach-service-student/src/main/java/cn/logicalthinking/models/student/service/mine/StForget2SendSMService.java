package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.constant.RedisConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.enums.SmsEnum;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.cache.RedisManager;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.ParamValidation;
import cn.logicalthinking.common.util.SmsCodeGen;
import cn.logicalthinking.common.util.TencentSMS;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author xhx
 * @version 1.0
 * @Description 忘记密码，发送验证码
 * @date 2018-12-26
 */
@Service
public class StForget2SendSMService extends AbstractService {

    @Resource
    private RedisManager redisManager;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String phone = data.getParameter("phone");
        ParamValidation.isNotNull(phone, "电话不能为空");
        ParamValidation.isPhoneNum(phone);

        Student studentUser = data.getStudentUser();
        if (!Objects.equals(phone, studentUser.getPhone())) {
            throw new BusinessException("手机号不正确,请输入该用户绑定的手机号");
        }

        String code = SmsCodeGen.getCode();

        int redisSmsCodeTimeout = RedisConstant.REDIS_SMS_CODE_TIMEOUT;

        int minute = (redisSmsCodeTimeout % 3600000) / 60000;

        String[] parm = {code,minute+""};
        SmsSingleSenderResult result = TencentSMS.sengSMS(phone, SmsEnum.FOR_LOGIN, parm);
        if (!Objects.equals(result.errMsg, "OK")) {
            throw new BusinessException("发送失败");
        }
        redisManager.set(RedisConstant.STUDENT_FORGET_SMS_CODE + phone, code,RedisConstant.REDIS_SMS_CODE_TIMEOUT);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), "校验成功");
    }

}
