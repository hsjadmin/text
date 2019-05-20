package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MessageTeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note 删除当前用户所有信息
 * @auhtor 卢祖飞
 * @date 2018/10/17,17:14
 */
@Service
public class RemoveTeacherMessageService extends AbstractService {

    @Resource
    private MessageTeacherDao messageTeacherDao;


    @Override
    protected Result doWork(IClientData data) throws Exception {

        Teacher teacher = data.getTeacherUser();

        int num = messageTeacherDao.removeMessageTeacherBytId(teacher.getId().toString());

        if(num > 0){
            return Result.jsonMsg(CODE.CODE_200_DELETE.getKey(),CODE.CODE_200_DELETE.getValue());
        }
            return Result.jsonMsg(CODE.CODE_509.getKey(),CODE.CODE_509.getValue());
    }
}
