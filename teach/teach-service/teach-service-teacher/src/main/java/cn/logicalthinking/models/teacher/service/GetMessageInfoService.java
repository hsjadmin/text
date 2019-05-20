package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MessageTeacherDao;
import cn.logicalthinking.common.entity.MessageTeacher;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @note 查看消息详情
 * @auhtor 卢祖飞
 * @date 2018/12/26,15:52
 */
@Service
public class GetMessageInfoService extends AbstractService {

    @Resource
    private MessageTeacherDao messageTeacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = new HashMap<String, Object>();

        //获取1当前用户
        Teacher teacherUser = data.getTeacherUser();

        //获取查询的消息id
        String messageId = data.getParameter("messageId");

        ParamValidation.isNotNull(teacherUser,"登录超时,请重试");
        ParamValidation.isNotNull(messageId,"msgId为空");

        map.put("teacherId",teacherUser.getId());
        map.put("id",messageId);

        //修改已读状态
        messageTeacherDao.updateReadStatus(map);

        MessageTeacher messageTeacher = messageTeacherDao.selectMessageTeacherById(messageId);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),messageTeacher);
    }
}
