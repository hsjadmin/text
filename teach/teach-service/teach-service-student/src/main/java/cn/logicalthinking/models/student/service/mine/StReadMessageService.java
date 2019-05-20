package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MessageStudentDao;
import cn.logicalthinking.common.entity.MessageStudent;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 读取消息
 * @date 2018-12-19
 */
@Service
public class StReadMessageService extends AbstractService {

    @Resource
    private MessageStudentDao messageStudentDao;

    private IClientData data;

    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String id = data.getParameter("id");
        ParamValidation.isNotNull(id, "消息id找不到");

        MessageStudent messageStudent = messageStudentDao.selectMessageStudentById(Integer.parseInt(id));
        messageStudent.setStatus(1);
        if (messageStudentDao.updateMessageStudent(messageStudent) == 0) {
            throw new BusinessException("读取失败");
        }

        return Result.jsonData(CODE.CODE_200_UPDATE.getKey(), messageStudent);

    }

}
