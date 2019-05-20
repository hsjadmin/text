package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @note 设置疑难收费
 * @auhtor 卢祖飞
 * @date 2018/12/27,18:31
 */
@Service
public class SetProblemSolvingStatusService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        //获取老师信息
        Teacher teacherUser = data.getTeacherUser();
        //起步价
        String startingPrice = data.getParameter("startingPrice");
        //超时收费
        String chargeSettings = data.getParameter("chargeSettings");

        ParamValidation.isNotNull(teacherUser,"登录超时,请重新登录");

        if("".equals(chargeSettings) || chargeSettings == null){
            chargeSettings = "0";
        }
        if("".equals(startingPrice) || startingPrice == null){
            startingPrice = "0";
        }

        Teacher teacher = new Teacher();

        teacher.setId(teacherUser.getId());
        try {
            teacher.setChargeSettings(new BigDecimal(chargeSettings));
        } catch (Exception e) {
            throw new ValidataException("超时收费格式异常");
        }
        try {
            teacher.setStartingPrice(new BigDecimal(startingPrice));
        } catch (Exception e) {
            throw new ValidataException("起步价格式异常");
        }

        teacherDao.updateTeacher(teacher);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
    }
}
