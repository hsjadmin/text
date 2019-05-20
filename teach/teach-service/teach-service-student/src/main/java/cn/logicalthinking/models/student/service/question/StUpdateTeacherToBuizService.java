package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @note
 * @auhtor xhx
 * @date 2019/1/5,13:54
 */
@Service
public class StUpdateTeacherToBuizService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String phone = data.getParameter("phone");
        ParamValidation.isNotNull(phone, "手机号不能为空");
        Map<String, Object> map = new HashMap<>(2);
        map.put("phone", phone);
        map.put("isOnline", "1");
        if (teacherDao.updateOnlineStatusByPhone(map) == 0) {
            throw new BusinessException("操作失败");
        }

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), CODE.CODE_200_UPDATE.getValue());
    }
}
