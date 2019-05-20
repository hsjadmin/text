package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.entity.Course;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 查询指定老师开设的课程
 * @date 2018-12-19
 */
@Service
public class StGetCourseListService extends AbstractService {

    @Resource
    private CourseDao courseDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

//        String[] conditionArr = new String[]{
//                "id",//主键
//                "createTime",//创建时间
//                "updateTime",//修改时间
//                "teacherId",//老师id
//                "name",//课程名
//                "grade",//年级
//                "subject",//科目
//                "introduce",//简介
//                "status",//状态(0有效  1无效)
//                "courseType",//科目类型（0小学 1初中  2高中）
//                "isShow",//是否显示（0显示  1不显示）
//                "liveStatus",//是否正在直播（0否，1是）
//                "isFinish",//老师上完课依据（0否，1是），课程所有类型及章节是否完成
//        };


        String id = data.getParameter("id");
        map.put("teacherId", id);
        // 有效
        map.put("status", 0);
        // 显示
        map.put("isShow", 0);

//        data.setConditionMap(map, conditionArr);

        PageInfo<Course> pageInfo = courseDao.selectCourseListByPage(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), pageInfo.getList(), pageInfo.getTotal());
    }

}
