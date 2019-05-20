package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.QuestionCommentDao;
import cn.logicalthinking.common.entity.QuestionComment;
import cn.logicalthinking.common.util.ParamValidation;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 列表查询 答疑评论
 * @date 2018-12-19
 */
@Service
public class StGetQuestionCommentListService extends AbstractService {

    @Resource
    private QuestionCommentDao questionCommentDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

        String[] conditionArr = new String[]{
                "type",//评论类型 （0好评1中评2差评）
        };

        String teacherId = data.getParameter("teacherId");
        ParamValidation.isNotNull(teacherId, "缺失老师id");
        map.put("teacherId", teacherId);

        data.setConditionMap(map, conditionArr);

        PageInfo<QuestionComment> pageInfo = questionCommentDao.selectQuestionCommentListByPage(map);

        // 查询各评论类型数量
        Map<String, Object> mapCount = new HashMap<>();
        mapCount.put("teacherId", teacherId);
        // 所有
        int all = questionCommentDao.selectQuestionCommentCount(mapCount);

        // 好评
        mapCount.put("type", 0);
        int good = questionCommentDao.selectQuestionCommentCount(mapCount);
        // 中评
        mapCount.put("type", 1);
        int normal = questionCommentDao.selectQuestionCommentCount(mapCount);
        // 差评
        mapCount.put("type", 2);
        int bad = questionCommentDao.selectQuestionCommentCount(mapCount);

        Result result = Result.jsonData(CODE.CODE_200_SELECT.getKey(), pageInfo.getList(), pageInfo.getTotal());
        result.put("all", all);
        result.put("good", good);
        result.put("normal", normal);
        result.put("bad", bad);

        return result;

    }

}
