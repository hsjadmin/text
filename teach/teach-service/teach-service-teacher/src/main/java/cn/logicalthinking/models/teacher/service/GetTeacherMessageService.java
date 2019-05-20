package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MessageTeacherDao;
import cn.logicalthinking.common.entity.MessageTeacher;
import cn.logicalthinking.common.entity.Teacher;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @note 查询老师所有消息
 * @auhtor 卢祖飞
 * @date 2018/12/26,15:43
 */
@Service
public class GetTeacherMessageService extends AbstractService {

    @Resource
    private MessageTeacherDao messageTeacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

        Teacher teacherUser = data.getTeacherUser();

        map.put("teacherId",teacherUser.getId());

        PageInfo<MessageTeacher> pageInfo = messageTeacherDao.selectMessageByTeacherId(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
    }
}
