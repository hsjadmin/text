package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.FinanceStudentDao;
import cn.logicalthinking.common.entity.FinanceStudent;
import cn.logicalthinking.common.entity.Student;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生的账户费用明细 列表查询
 * @date 2018-12-19
 */
@Service
public class StGetFinanceStudentListService extends AbstractService {

    @Resource
    private FinanceStudentDao financeStudentDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

        Student studentUser = data.getStudentUser();
        map.put("studentId", studentUser.getId());

        PageInfo<FinanceStudent> pageInfo = financeStudentDao.selectFinanceStudentListByPage(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), pageInfo.getList(), pageInfo.getTotal());
    }

}
