package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.QuestionnaireDao;
import cn.logicalthinking.common.entity.Questionnaire;
import cn.logicalthinking.common.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 卢祖飞
 * @version 1.0
 * @Description 问卷题目表 添加
 * @date 2018-12-19
 */
@Service
public class AddQuestionnaireService extends AbstractService {

    @Resource
    private QuestionnaireDao questionnaireDao;

    private IClientData data;


    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        List<Questionnaire> questionnaires = new ArrayList<>();

        String question = data.getParameter("question");

        String[] split = question.split(",");
        questionnaireDao.removeAllQuestionnaire();

        for (int i = 0; i < split.length; i++) {
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setCreateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));
            questionnaire.setQuestion(split[i]);
            questionnaire.setIsShow(0);
            questionnaires.add(questionnaire);
        }

        questionnaireDao.batchQuestionnaire(questionnaires);

        return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), CODE.CODE_200_ADD.getValue());
    }


}
