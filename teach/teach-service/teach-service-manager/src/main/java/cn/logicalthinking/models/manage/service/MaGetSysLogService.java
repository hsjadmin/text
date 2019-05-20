package cn.logicalthinking.models.manage.service;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysLogDao;
import cn.logicalthinking.common.entity.SysLog;

import com.github.pagehelper.PageInfo;

/**
 * @note 查询系统日志
 * @auhtor 肖宏鑫
 * @date 2018-11-22
 */
@Service
public class MaGetSysLogService extends AbstractService {

    @Resource
    private SysLogDao sysLogDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Map<String,Object> map = data.initMap();

        String[] conditionArr = new String[]{
                "operateName" ,//课程名
                "username" ,//课程类型，直接存语文数学等
                "createTime" ,//状态（0上架  1下架）
        };

        data.setConditionMap(map,conditionArr);

        PageInfo<SysLog> pageInfo = sysLogDao.selectSysLogListByPage(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
    }
}
