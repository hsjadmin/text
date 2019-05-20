package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 卢祖飞
 * @version 1.0
 * @Description 学生表 修改
 * @date 2018-12-19
 */
@Service
public class UpdateStudentService extends AbstractService {

    @Resource
    private StudentDao studentDao;

    private IClientData data;

    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Student student = (Student) data.getObject();

        if (student.getId() == null)
            throw new ValidataException("id不能为空");

        student.setUpdateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));

        studentDao.updateStudent(student);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), CODE.CODE_200_UPDATE.getValue());

    }

}
