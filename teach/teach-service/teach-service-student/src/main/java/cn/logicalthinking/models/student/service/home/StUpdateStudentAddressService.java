package cn.logicalthinking.models.student.service.home;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 修改学生地址接口
 * @date 2018-12-19
 */
@Service
public class StUpdateStudentAddressService extends AbstractService {

    @Resource
    private StudentDao studentDao;

    @Override
    protected void before(IClientData data) {

        String address = data.getParameter("address");
        String region = data.getParameter("region");
        ParamValidation.isNotNull(region, "区域不能为空");
        ParamValidation.isNotNull(address, "地址不能为空");

    }

    protected Result doWork(IClientData data) throws Exception {

        String address = data.getParameter("address");
        String region = data.getParameter("region");

        Student studentUser = data.getStudentUser();
        Student temp = new Student();
        temp.setId(studentUser.getId());
        temp.setUpdateTime(DateUtil.getSimpleCurrentDate());
        temp.setAddress(address);
        temp.setRegion(region);

        if (studentDao.updateStudent(temp) == 0) {
            throw new BusinessException("操作失败");
        }

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), "信息修改成功");

    }

}
