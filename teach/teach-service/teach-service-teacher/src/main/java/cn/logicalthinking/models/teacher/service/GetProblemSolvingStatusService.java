package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.entity.Teacher;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @note 查询疑难设置
 * @auhtor 卢祖飞
 * @date 2018/12/28,10:48
 */
@Service
public class GetProblemSolvingStatusService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private ApplicationParameterDao applicationParameterDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacherUser = data.getTeacherUser();

        Map<String, Object> map = new HashMap<>();

        map.put("name","startingTime");

        ApplicationParameter applicationParameter = applicationParameterDao.selectApplicationParameterByName(map);

        Teacher teacher = teacherDao.selectTeacherById(teacherUser.getId());

        JSONObject jsonObject = (JSONObject) JSON.toJSON(applicationParameter);
        jsonObject.put("teacherName",teacher.getName());
        jsonObject.put("chargeSettings",teacher.getChargeSettings());
        jsonObject.put("startingPrice",teacher.getStartingPrice());

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),jsonObject);
    }
}
