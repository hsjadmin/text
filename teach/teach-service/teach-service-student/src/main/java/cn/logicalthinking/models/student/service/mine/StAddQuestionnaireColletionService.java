package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.QuestionnaireColletionDao;
import cn.logicalthinking.common.entity.QuestionnaireColletion;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 问卷情况汇总表 添加
 * @date 2018-12-19
 */
@Service
public class StAddQuestionnaireColletionService extends AbstractService {

    @Resource
    private QuestionnaireColletionDao questionnaireColletionDao;

    private IClientData data;


    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        QuestionnaireColletion qc = (QuestionnaireColletion) data.getObject();

        ParamValidation.isNotNull(qc, "问卷内容为空");

        // 计算满意度
        int total = Integer.parseInt(qc.getAnswer1()) +
                Integer.parseInt(qc.getAnswer2()) +
                Integer.parseInt(qc.getAnswer3()) +
                Integer.parseInt(qc.getAnswer4()) +
                Integer.parseInt(qc.getAnswer5()) +
                Integer.parseInt(qc.getAnswer6()) +
                Integer.parseInt(qc.getAnswer7()) +
                Integer.parseInt(qc.getAnswer8());
        qc.setSatisfied(String.valueOf(total / 8));

        // 设置答题人
        Student studentUser = data.getStudentUser();
        qc.setName(studentUser.getName());
        qc.setAccount(studentUser.getUserName());


        if (questionnaireColletionDao.addQuestionnaireColletion(qc) == 0) {
            throw new BusinessException("提交失败");
        }

        return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), CODE.CODE_200_ADD.getValue());

    }


}
