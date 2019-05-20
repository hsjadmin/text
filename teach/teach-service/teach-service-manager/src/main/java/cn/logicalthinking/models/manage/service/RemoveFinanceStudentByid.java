package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.FinanceStudentDao;
import cn.logicalthinking.common.exception.ValidataException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2018/12/20,16:54
 */
@Service
public class RemoveFinanceStudentByid extends AbstractService {

    @Resource
    private FinanceStudentDao financeStudentDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = new HashMap<>();

        String id = data.getParameter("ids");

        if(StringUtils.isBlank(id)){
            throw new ValidataException("id不能为空");
        }

        map.put("ids",getIds(id));

        financeStudentDao.removeFinanceStudent(map);

        return Result.jsonMsg(CODE.CODE_200_DELETE.getKey(),CODE.CODE_200_DELETE.getValue());
    }

    private static String getIds(String idsStr){
        String ids="";
        if(StringUtils.isBlank(idsStr))
            return ids;

        String[] arr=idsStr.split(",");
        for (int i = 0; i < arr.length; i++) {
            ids+="'"+arr[i]+"',";
        }
        if(StringUtils.isBlank(ids))
            return idsStr;
        if(ids.lastIndexOf(",")!=-1)
            ids=ids.substring(0,ids.lastIndexOf(","));
        return ids;
    }
}
