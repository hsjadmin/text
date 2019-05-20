package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @version 1.0
 * @date 2018-09-28
 */
@Component
public class SysUserDaoImpl extends BaseDao implements SysUserDao {

    public int addSysUser(SysUser sysUser) throws DaoException {
        return super.getSqlSession().insert("addSysUser", sysUser);
    }

    public int batchSysUser(List<SysUser> list) throws DaoException {
        return super.getSqlSession().insert("batchSysUser", list);
    }

    public int updateSysUser(SysUser sysUser) throws DaoException {
        return super.getSqlSession().update("updateSysUser", sysUser);
    }

    public int removeSysUser(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().delete("removeSysUser", map);
    }

    public SysUser selectSysUserById(String id) throws DaoException {
        return super.getSqlSession().selectOne("selectSysUserById", id);
    }

    public List<SysUser> selectSysUserListAll(Map<String, Object> map) throws DaoException {
        return super.getSqlSession().selectList("selectSysUserListAll", map);
    }

    public PageInfo<SysUser> selectSysUserListByPage(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<SysUser> selectList = super.getSqlSession().selectList("selectSysUserListByPage", map);
        return new PageInfo<SysUser>(selectList);
    }

    public int selectSysUserCount(Map<String, Object> map) throws DaoException {
        Object obj = super.getSqlSession().selectOne("selectSysUserCount", map);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        return 0;
    }

    /*********************************************自定义扩展sql***********************************************/

    public PageInfo<SysUser> selectSysUserRoleListByPage(Map<String, Object> map) throws DaoException {
        if (!map.containsKey("pageNum"))
            map.put("pageNum", 0);
        if (!map.containsKey("pageSize"))
            map.put("pageSize", 0);
        PageHelper.startPage(map);
        List<SysUser> selectList = super.getSqlSession().selectList("selectSysUserRoleListByPage", map);
        return new PageInfo<SysUser>(selectList);
    }


}