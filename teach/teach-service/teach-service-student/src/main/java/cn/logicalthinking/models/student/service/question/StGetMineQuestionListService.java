package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.enums.OrderQuestionStatus;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.entity.OrderQuestion;
import cn.logicalthinking.common.entity.Student;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 查询我的解疑
 * @date 2018-12-19
 */
@Service
public class StGetMineQuestionListService extends AbstractService {

    @Resource
    private OrderQuestionDao orderQuestionDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Student studentUser = data.getStudentUser();
        Map<String, Object> map = data.initMap();
        map.put("studentId", studentUser.getId());
        map.put("status", OrderQuestionStatus.FINISHED.getKey());


        PageInfo<OrderQuestion> pageInfo = orderQuestionDao.selectOrderQuestionListByPage(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), pageInfo.getList(), pageInfo.getTotal());
    }

}
