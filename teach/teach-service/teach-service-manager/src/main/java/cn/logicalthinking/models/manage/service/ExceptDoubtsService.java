package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.GenderEnum;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.dao.SysLogDao;
import cn.logicalthinking.common.entity.OrderCourse;
import cn.logicalthinking.common.entity.OrderQuestion;
import cn.logicalthinking.common.entity.SysLog;
import cn.logicalthinking.common.util.ExcelExport;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/4,11:53
 */
@Service
public class ExceptDoubtsService extends AbstractService {

    @Resource
    private OrderQuestionDao orderQuestionDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Map<String, Object> map = data.initMap();

        String[] conditionArr = new String[]{
                "id",
                "createTime",
                "updateTime",
                "studentId",
                "teacherId",
                "studentName",
                "teacherName",
                "questionImg",
                "status",
                "price",
                "isCoupon",
                "couponPrice",
                "isPay",
                "payType",
                "phone",
                "address",
                "area",
                "answeringTime",
                "apprised",
                "truePrice"
        };

        data.setConditionMap(map, conditionArr);

        List<OrderQuestion> list = orderQuestionDao.selectOrderQuestionListAll(map);

        ExcelExport excel = new ExcelExport(data.getResponse(), "疑难订单", "疑难订单");
        // 设置对应实体类属性
        String titleColumn[] = {"studentName", "teacherName", "status", "price", "isCoupon","couponPrice"
        };
        // 设置ExEcel标题
        String titleName[] = {"学生姓名", "老师姓名", "状态", "价格", "是否使用优惠券", "优惠券金额"};
        // 设置列宽
        int titleSize[] = {20, 30, 40, 40, 30, 20};

        ArrayList<JSONObject> arrayList = new ArrayList<>(list.size());
        for (OrderQuestion orderQuestion : list) {
            JSONObject jsonObject = (JSONObject) JSON.toJSON(orderQuestion);
            Integer status = orderQuestion.getStatus();
            String orderStatus = "";
            if(status == 0)
                orderStatus = "待接收";
            else if (status == 1)
                orderStatus = "解疑中";
            else if (status == 2)
                orderStatus = "待支付";
            else if (status == 3)
                orderStatus = "已完成";
            else if (status == 4)
                orderStatus = "已通过";
            else if (status == 5)
                orderStatus = "已取消";

            jsonObject.put("status", orderStatus);
            jsonObject.put("isCoupon", orderQuestion.getIsCoupon() == null || orderQuestion.getIsCoupon() == 0 ? "否" : "是");
            arrayList.add(jsonObject);
        }

        excel.wirteExcelViaJson(titleColumn, titleName, titleSize, arrayList);

        return null;
    }

    private static String getIds(String idsStr) {
        String ids = "";
        if (StringUtils.isBlank(idsStr))
            return ids;

        String[] arr = idsStr.split(",");
        for (int i = 0; i < arr.length; i++) {
            ids += "'" + arr[i] + "',";
        }
        if (StringUtils.isBlank(ids))
            return idsStr;
        if (ids.lastIndexOf(",") != -1)
            ids = ids.substring(0, ids.lastIndexOf(","));
        return ids;
    }
}
