package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.LGS;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/5,11:14
 */
@Service
public class GetLGSService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        List<LGS> lgs = teacherDao.selectLGS();

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),lgs);
    }
}
