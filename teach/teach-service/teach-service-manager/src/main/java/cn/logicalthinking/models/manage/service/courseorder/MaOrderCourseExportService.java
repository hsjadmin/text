package cn.logicalthinking.models.manage.service.courseorder;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.GenderEnum;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderCourseDao;
import cn.logicalthinking.common.entity.OrderCourse;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.ExcelExport;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 课程订单 列表查询
 * @date 2018-12-19
 */
@Service
public class MaOrderCourseExportService extends AbstractService {

    @Resource
    private OrderCourseDao orderCourseDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

        String[] conditionArr = new String[]{
                "id",//主键
                "createTime",//创建时间
                "updateTime",//修改时间
                "studentName",//学生姓名
                "gender",//性别（0男 1女）
                "address",//地址
                "phone",//手机号
                "orig",//原价，缩写
                "discount",//优惠价
                "periods",//课时
                "duration",//课程时长，分钟
                "courseTime",//上课时间
                "teacherName",//老师姓名
                "isCoupon",//是否使用优惠券
                "couponPrice",//优惠券金额
                "teacherBonus",//教师提成
                "studentId",//学生id
                "teacherId",//老师id
                "courseId",//课程id
                "courseTypeId",//课程类型id
        };

        data.setConditionMap(map, conditionArr);

        List<OrderCourse> list = orderCourseDao.selectOrderCourseListAll(map);

        if (list == null || list.isEmpty()) {
            throw new BusinessException("无数据导出");
        }

        ExcelExport excel = new ExcelExport(data.getResponse(), "课程订单信息", "课程订单信息");
        // 设置对应实体类属性
        String titleColumn[] = {
                "studentName",//学生姓名
                "gender",//性别（0男 1女）
                "address",//地址
                "phone",//手机号
                "orig",//原价，缩写
                "discount",//优惠价
                "periods",//课时
                "duration",//课程时长，分钟
                "courseTime",//上课时间
                "teacherName",//老师姓名
                "isCoupon",//是否使用优惠券
                "couponPrice",//优惠券金额
        };
        // 设置ExEcel标题
        String titleName[] = {"学生姓名", "性别", "地址", "手机号", "原价", "优惠价", "课时", "课程时长", "上课时间",
                "老师姓名", "是否使用优惠券", "优惠券金额"};
        // 设置列宽
        int titleSize[] = {30, 30, 40, 30, 40, 30, 30, 30, 30, 30, 30, 30};

        ArrayList<JSONObject> arrayList = new ArrayList<>(list.size());
        for (OrderCourse orderCourse : list) {
            JSONObject jsonObject = (JSONObject) JSON.toJSON(orderCourse);
            jsonObject.put("gender", GenderEnum.getValueBykey(orderCourse.getGender()));
            Integer isCoupon = orderCourse.getIsCoupon();
            jsonObject.put("isCoupon", isCoupon == null || isCoupon == 0 ? "否" : "是");
            arrayList.add(jsonObject);
        }

        excel.wirteExcelViaJson(titleColumn, titleName, titleSize, arrayList);
        return null;
    }

}
