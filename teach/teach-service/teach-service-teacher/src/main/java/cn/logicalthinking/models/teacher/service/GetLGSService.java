package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.constant.RedisConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.cache.RedisManager;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Level;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GetLGSService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private RedisManager redisManager;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String cache = null;
        try {
            cache = redisManager.get(RedisConstant.LGS_FOR_TEACHER);
        } catch (Exception e) {
            cache = null;
        }
        if (StringUtils.isBlank(cache)) {
            List<Level> level = teacherDao.selectLevel();

            String jsonString = JSONObject.toJSONString(level);
            cache = jsonString.replaceAll("levelId", "value")
                    .replaceAll("levelName", "label")
                    .replaceAll("gradeId", "value")
                    .replaceAll("gradeName", "label")
                    .replaceAll("subjectId", "value")
                    .replaceAll("subjectName", "label")
                    .replaceAll("subjects", "children")
                    .replaceAll("grades", "children")
            ;

            redisManager.set(RedisConstant.LGS_FOR_TEACHER, cache);
        }

        return Result.jsonData("200", JSON.parse(cache));
    }
}
