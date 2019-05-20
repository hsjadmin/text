package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.MD5;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author xhx
 * @version 1.0
 * @Description 校验旧密码
 * @date 2018-12-26
 */
@Service
public class StVerifyOldPINService extends AbstractService {

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Student studentUser = data.getStudentUser();

        String oldPIN = data.getParameter("oldPIN");

        ParamValidation.isNotNull(oldPIN, "未输入密码");

        if (!Objects.equals(MD5.md5(oldPIN), studentUser.getPin())) {
            throw new BusinessException("支付密码输入错误");
        }

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), "校验成功");
    }

}
