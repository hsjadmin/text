package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MessageStudentDao;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 读取全部消息
 * @date 2018-12-19
 */
@Service
public class StReadAllMessageService extends AbstractService {

    @Resource
    private MessageStudentDao messageStudentDao;

    private IClientData data;

    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Student studentUser = data.getStudentUser();

        if (messageStudentDao.readAll(studentUser.getId()) == 0) {
            throw new BusinessException("操作失败");
        }

        return Result.jsonData(CODE.CODE_200_UPDATE.getKey(), CODE.CODE_200_UPDATE.getValue());

    }

}
