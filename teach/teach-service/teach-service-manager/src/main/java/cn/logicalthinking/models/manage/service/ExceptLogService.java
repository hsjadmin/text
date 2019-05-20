package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysLogDao;
import cn.logicalthinking.common.entity.SysLog;
import cn.logicalthinking.common.util.ExcelExport;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/4,11:53
 */
@Service
public class ExceptLogService extends AbstractService {

    @Resource
    private SysLogDao sysLogDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Map<String, Object> map = data.initMap();

        String[] conditionArr = new String[]{
                "logid",
                "userId",
                "username",
                "operateName",
                "operateUrl",
                "token",
                "ip",
                "port",
                "customerType",
                "type",
                "createTime",
                "loseStatus",
                "opType"
        };

        data.setConditionMap(map, conditionArr);

        List<SysLog> list = sysLogDao.selectSysLogListAll(map);

        ExcelExport excel = new ExcelExport(data.getResponse(), "系统日志", "系统日志");
        // 设置对应实体类属性
        String titleColumn[] = {"username", "operateName", "type", "createTime",  "opType"
        };
        // 设置ExEcel标题
        String titleName[] = {"用户名", "操作名称",  "类型", "操作时间","操作类型"};
        // 设置列宽
        int titleSize[] = {20, 30, 30, 40, 40};

        list = getLog(list);

        excel.wirteExcel(titleColumn, titleName, titleSize, list);

        return null;
    }

    private List<SysLog> getLog(List<SysLog> list){
        for (SysLog sysLog : list) {
            if(sysLog.getType() == "1"){
                sysLog.setType("订单操作");
            }else if(sysLog.getType() == "2"){
                sysLog.setType("基础信息操作");
            }else if(sysLog.getType() == "3"){
                sysLog.setType("用户操作");
            }

            if(sysLog.getOpType() == "1"){
                sysLog.setOpType("新增");
            }else if(sysLog.getOpType() == "2"){
                sysLog.setOpType("修改");
            }else if(sysLog.getOpType() == "3"){
                sysLog.setOpType("删除");
            }else if(sysLog.getOpType() == "4"){
                sysLog.setOpType("确认");
            }else if(sysLog.getOpType() == "5"){
                sysLog.setOpType("取消");
            }else if(sysLog.getOpType() == "6"){
                sysLog.setOpType("审核");
            }else if(sysLog.getOpType() == "7"){
                sysLog.setOpType("取消审核");
            }else if(sysLog.getOpType() == "8"){
                sysLog.setOpType("启用");
            }else if(sysLog.getOpType() == "9"){
                sysLog.setOpType("禁用");
            }
        }
        return list;
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
