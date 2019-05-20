package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseCommentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/4,10:00
 */
@Service
public class GetCourseCommentCountService extends AbstractService {

    @Resource
    private CourseCommentDao courseCommentDao;

    private IClientData data;


    @Override
    protected Result doWork(IClientData data) throws Exception {

        //获取课程id
        String cId = data.getParameter("cId");

        Map<String, Object> map = new HashMap<>();

        map.put("courseId",cId);
        //全部评论条数
        int allNum = courseCommentDao.selectCourseCommentCount(map);
        //好评
        map.put("type","0");
        int goodNum = courseCommentDao.selectCourseCommentCount(map);
        //中评
        map.put("type","1");
        int middleNum = courseCommentDao.selectCourseCommentCount(map);
        //差评
        map.put("type","2");
        int differenceNum = courseCommentDao.selectCourseCommentCount(map);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("allNum",allNum);
        map1.put("goodNum",goodNum);
        map1.put("middleNum",middleNum);
        map1.put("differenceNum",differenceNum);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),map1);
    }
}
