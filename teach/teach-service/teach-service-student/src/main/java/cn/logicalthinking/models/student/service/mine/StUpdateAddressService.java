package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.AddressDao;
import cn.logicalthinking.common.entity.Address;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生的收货地址表 修改
 * @date 2018-12-19
 */
@Service
public class StUpdateAddressService extends AbstractService {

    @Resource
    private AddressDao addressDao;

    private IClientData data;

    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Address address = (Address) data.getObject();
        ParamValidation.isNotNull(address.getId(), "地址id不能为空");

        addressDao.updateAddress(address);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), CODE.CODE_200_UPDATE.getValue());

    }

}
