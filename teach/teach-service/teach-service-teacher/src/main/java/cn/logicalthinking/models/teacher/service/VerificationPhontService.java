package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.constant.RedisConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.cache.RedisManager;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note 忘记密码/首次设置密码验证身份
 * @auhtor 卢祖飞
 * @date 2018/12/26,17:56
 */
@Service
public class VerificationPhontService extends AbstractService {

    @Resource
    private RedisManager redisManager;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacher = data.getTeacherUser();

        String phone = data.getParameter("phone");

        String code = data.getParameter("code");

        isNull(teacher,phone,code);

        phone = phone.trim();

        code = code.trim();

        String codeSms = redisManager.get(RedisConstant.TEACHER_FORGET_SMS_CODE + phone);

        if(!phone.equals(teacher.getPhone())){
            throw new ValidataException("输入手机号与当前账户不匹配");
        }

        if(!code.equals(codeSms)){
            throw new ValidataException("验证码不正确");
        }
        return Result.jsonMsg(CODE.CODE_200_SELECT.getKey(),"验证成功,可修改密码");
    }

    private void isNull(Teacher teacher,String phone,String code){
        ParamValidation.isNotNull(teacher,"登录超时，请重新登录");
        ParamValidation.isNotNull(phone,"请输入手机号");
        ParamValidation.isNotNull(code,"请输入验证码");
    }
}
