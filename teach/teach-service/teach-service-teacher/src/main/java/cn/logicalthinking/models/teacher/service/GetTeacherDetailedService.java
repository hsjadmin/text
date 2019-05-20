package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.FinanceTeacherDao;
import cn.logicalthinking.common.entity.FinanceTeacher;
import cn.logicalthinking.common.entity.Teacher;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @note 查询老师所有明细
 * @auhtor 卢祖飞
 * @date 2018/12/26,17:48
 */
@Service
public class GetTeacherDetailedService extends AbstractService {

    @Resource
    private FinanceTeacherDao financeTeacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

        Teacher teacher = data.getTeacherUser();

        map.put("teacherId",teacher.getId());

        PageInfo<FinanceTeacher> pageInfo = financeTeacherDao.getFinanceTeacherbyTeacherId(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
    }
}
