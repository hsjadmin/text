package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.AddressDao;
import cn.logicalthinking.common.entity.Address;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

/**
 * @author xhx
 * @version 1.0
 * @Description 设置为默认地址
 * @date 2018-12-19
 */
@Service
public class StSetDefaultAddressService extends AbstractService {

    @Resource
    private AddressDao addressDao;

    private IClientData data;

    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String id = data.getParameter("id");
        int i = Integer.parseInt(id);

        Student studentUser = data.getStudentUser();

        // 全设置为非默认
        LinkedHashMap<String, Object> map = new LinkedHashMap<>(1);
        map.put("studentId", studentUser.getId());
        if (addressDao.setNotDefault(map) == 0) {
            throw new BusinessException("操作失败");
        }

        // 设为默认
        Address address = new Address();
        address.setId(i);
        address.setDefaulting("1");

        if (addressDao.updateAddress(address) == 0) {
            throw new BusinessException("操作失败");
        }

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), CODE.CODE_200_UPDATE.getValue());

    }

}
