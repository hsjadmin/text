package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/2,17:02
 */
@Service
public class SetUpTeacherAddressService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Teacher teacherUser = data.getTeacherUser();
        //省市区
        String region = data.getParameter("region");
        //详细地址
        String address = data.getParameter("address");

        Teacher teacher = new Teacher();
        teacher.setId(teacherUser.getId());
        teacher.setRegion(region);
        teacher.setAddress(address);

        teacherDao.updateTeacher(teacher);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
    }
}
