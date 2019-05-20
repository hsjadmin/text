package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.constant.RedisConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.cache.RedisManager;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author xhx
 * @version 1.0
 * @Description 校验旧密码
 * @date 2018-12-26
 */
@Service
public class StVerifySMSCodeService extends AbstractService {

    @Resource
    private RedisManager redisManager;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String smsCode = data.getParameter("SMSCode");
        String phone = data.getParameter("phone");

        ParamValidation.isPhoneNum(phone);
        ParamValidation.isNotNull(smsCode, "验证码不能为空");

        Student studentUser = data.getStudentUser();
        if (!Objects.equals(phone, studentUser.getPhone())) {
            throw new BusinessException("手机号不正确,请输入该用户绑定的手机号");
        }

        String cachedSMSCode = redisManager.get(RedisConstant.STUDENT_FORGET_SMS_CODE + phone);

        ParamValidation.isNotNull(cachedSMSCode, "验证码已失效或不存在");

        if (!Objects.equals(cachedSMSCode, smsCode)) {
            throw new BusinessException("验证码不匹配");
        }

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), "校验成功");
    }

}
