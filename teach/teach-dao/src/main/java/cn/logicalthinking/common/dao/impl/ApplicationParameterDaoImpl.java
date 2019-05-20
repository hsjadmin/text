package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @version 1.0
 * @date 2018-12-19
 */
@Component
public class ApplicationParameterDaoImpl extends BaseDao implements ApplicationParameterDao {

    public int addApplicationParameter(ApplicationParameter applicationParameter) throws DaoException {
        return super.getSqlSession().insert("addApplicationParameter", applicationParameter);
    }

    public int batchApplicationParameter(List<ApplicationParameter> list) throws DaoException {
        return super.getSqlSession().insert("batchApplicationParameter", list);
    }

    public int updateApplicationParameter(ApplicationParameter applicationParameter) throws DaoException {
        return super.getSqlSession().update("updateApplicationParameter", applicationParameter);
    }

    public int removeApplicationParameter(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().delete("removeApplicationParameter", map);
    }

    public ApplicationParameter selectApplicationParameterById(String id) throws DaoException {
        return super.getSqlSession().selectOne("selectApplicationParameterById", id);
    }

    public List<ApplicationParameter> selectApplicationParameterListAll(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().selectList("selectApplicationParameterListAll", map);
    }

    public PageInfo<ApplicationParameter> selectApplicationParameterListByPage(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<ApplicationParameter> selectList = super.getSqlSession().selectList("selectApplicationParameterListByPage", map);
        return new PageInfo<ApplicationParameter>(selectList);
    }

    public int selectApplicationParameterCount(Map<String, Object> map) throws DaoException {
        Object obj = super.getSqlSession().selectOne("selectApplicationParameterCount", map);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        return 0;
    }

    /*********************************************自定义扩展sql***********************************************/
    @Override
    public int updateApplicationParameterbyName(ApplicationParameter applicationParameter) throws DaoException {
        return super.getSqlSession().update("updateApplicationParameterbyName", applicationParameter);
    }

    public ApplicationParameter selectApplicationParameterByName(Map<String, Object> map) throws DaoException {
        ApplicationParameter applicationParameter = super.getSqlSession().selectOne("selectApplicationParameterListByPage", map);
        return applicationParameter;
    }
    public ApplicationParameter selectApplicationParameterByName(String name) throws DaoException {
        ApplicationParameter applicationParameter = super.getSqlSession().selectOne("selectApplicationParameterByName", name);
        return applicationParameter;
    }
}