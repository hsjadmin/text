package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.FinanceTeacherDao;
import cn.logicalthinking.common.entity.FinanceTeacher;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/2,14:11
 */
@Service
public class UpdateTeacherWithdrawalStatusService extends AbstractService {

    @Resource
    private FinanceTeacherDao financeTeacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String id = data.getParameter("id");

        String isFinish = data.getParameter("isFinish");

        ParamValidation.isNotNull(id,"id不能为空");
        ParamValidation.isNotNull(isFinish,"是否完成");

        FinanceTeacher financeTeacher = new FinanceTeacher();

        financeTeacher.setId(Integer.parseInt(id));
        financeTeacher.setIsFinish(isFinish);

        financeTeacherDao.updateFinanceTeacher(financeTeacher);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
    }
}
