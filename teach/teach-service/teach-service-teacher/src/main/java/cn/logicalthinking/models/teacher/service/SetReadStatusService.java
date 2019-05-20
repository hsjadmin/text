package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MessageTeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @note 修改已读状态
 * @auhtor 卢祖飞
 * @date 2018/10/17,17:24
 */
@Service
public class SetReadStatusService extends AbstractService {

    @Resource
    private MessageTeacherDao messageTeacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        //获取当前登录用户信息
        this.data = data;

        Map<String, Object> map = new HashMap<String, Object>();

        Teacher teacherUser = data.getTeacherUser();

        ParamValidation.isNotNull(teacherUser,"登录超时,请重试");

        map.put("teacherId",teacherUser.getId());

        int num = messageTeacherDao.updateReadStatus(map);

        if(num > 0){
            return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
        }
            return Result.jsonMsg(CODE.CODE_509.getKey(),CODE.CODE_509.getValue());
    }
}
