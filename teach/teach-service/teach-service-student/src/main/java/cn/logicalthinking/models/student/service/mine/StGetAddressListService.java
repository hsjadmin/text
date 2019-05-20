package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.AddressDao;
import cn.logicalthinking.common.entity.Address;
import cn.logicalthinking.common.entity.Student;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生的收货地址表 列表查询
 * @date 2018-12-19
 */
@Service
public class StGetAddressListService extends AbstractService {

    @Resource
    private AddressDao addressDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

        String[] conditionArr = new String[]{
                "id",//主键
                "createTime",//创建时间
                "updateTime",//修改时间
                "studentId",//学生id
                "name",//姓名
                "phone",//手机号
                "area",//所在地区(省市区)
                "address",//详细地址
        };

        Student studentUser = data.getStudentUser();
        map.put("studentId", studentUser.getId());
        data.setConditionMap(map, conditionArr);

        PageInfo<Address> pageInfo = addressDao.selectAddressListByPage(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), pageInfo.getList(), pageInfo.getTotal());
    }

}
