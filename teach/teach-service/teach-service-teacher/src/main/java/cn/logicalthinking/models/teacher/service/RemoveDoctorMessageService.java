package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MessageTeacherDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @note 删除指定消息
 * @auhtor 卢祖飞
 * @date 2018/10/9,18:41
 */
@Service
public class RemoveDoctorMessageService extends AbstractService {


    @Resource
    private MessageTeacherDao messageTeacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = new HashMap<String, Object>();

        String ids = data.getParameter("ids");

        map.put("ids",getIds(ids));

        int num =messageTeacherDao.removeMessageTeacher(map);


        if(num > 0){
            return Result.jsonMsg(CODE.CODE_200_DELETE.getKey(),CODE.CODE_200_DELETE.getValue());
        }
            return Result.jsonMsg(CODE.CODE_509.getKey(),CODE.CODE_509.getValue());
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
