package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @note 查询老师余额与明细
 * @auhtor 卢祖飞
 * @date 2018/12/26,17:02
 */
@Service
public class GetTeacherBalanceService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacher = data.getTeacherUser();

        Teacher balance = teacherDao.getTeacherBalance(teacher.getId());
        balance.setBalance(balance.getBalance().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(balance.getBalance());
        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),balance);
    }
}
