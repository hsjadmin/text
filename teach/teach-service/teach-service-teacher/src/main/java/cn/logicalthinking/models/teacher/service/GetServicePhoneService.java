package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.entity.ApplicationParameter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @note 查询客服电话
 * @auhtor 卢祖飞
 * @date 2018/12/27,18:37
 */
@Service
public class GetServicePhoneService extends AbstractService {

    @Resource
    private ApplicationParameterDao applicationParameterDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        List<ApplicationParameter> applicationParameters = applicationParameterDao.selectApplicationParameterListAll(null);

        String phone = "";

        for (ApplicationParameter applicationParameter : applicationParameters) {
            if("customerServicePhone".equals(applicationParameter.getName())){
                phone = applicationParameter.getValue();
            }
        }
        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),phone);
    }
}
