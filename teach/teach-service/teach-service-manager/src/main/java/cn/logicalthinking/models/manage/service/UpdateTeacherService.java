package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 卢祖飞
 * @version 1.0
 * @Description 老师表 修改
 * @date 2018-12-19
 */
@Service
public class UpdateTeacherService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacher = (Teacher) data.getObject();

        if (teacher.getId() == null)
            throw new ValidataException("id不能为空");

        teacher.setUpdateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));

        teacherDao.updateTeacher(teacher);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), CODE.CODE_200_UPDATE.getValue());

    }

}
