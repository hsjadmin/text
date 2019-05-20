package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.QuestionnaireDao;
import cn.logicalthinking.common.entity.Questionnaire;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 问卷题目表 列表查询
 * @date 2018-12-19
 */
@Service
public class StGetQuestionnaireListService extends AbstractService {

    @Resource
    private QuestionnaireDao questionnaireDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

        String[] conditionArr = new String[]{
                "id",//主键
                "createTime",//创建时间
                "updateTime",//修改时间
                "question",//问题标题，固定八个题目
        };

        map.put("isShow","0");
        data.setConditionMap(map, conditionArr);

        List<Questionnaire> questionnaires = questionnaireDao.selectQuestionnaireListAll(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), questionnaires);
    }

}
