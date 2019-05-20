package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.constant.AppParamConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.entity.ApplicationParameter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生充值
 * @date 2018-12-19
 */
@Service
public class StGetAppParamService extends AbstractService {


    @Resource
    private ApplicationParameterDao applicationParameterDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Map<String, Object> map = new LinkedHashMap<>(1);
        map.put("name", AppParamConstant.CUSTOMER_SERVICE_PHONE);
        ApplicationParameter applicationParameter = applicationParameterDao.selectApplicationParameterByName(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), applicationParameter.getValue());
    }

}
