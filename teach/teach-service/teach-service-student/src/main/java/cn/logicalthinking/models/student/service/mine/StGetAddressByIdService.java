package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.AddressDao;
import cn.logicalthinking.common.entity.Address;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生的收货地址表 根据id查询
 * @date 2018-12-19
 */
@Service
public class StGetAddressByIdService extends AbstractService {

    @Resource
    private AddressDao addressDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String id = data.getParameter("id");
        int i = Integer.parseInt(id);

        Address address = addressDao.selectAddressById(i);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), address);
    }

}
