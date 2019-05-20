package cn.logicalthinking.models.manage.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.dao.UserRoleDao;
import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.entity.UserRole;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.MD5;
import cn.logicalthinking.common.util.ParamValidation;
import cn.logicalthinking.common.util.Tools;

/**
 * @author 黄世杰
 * @version 1.0
 * @Description 用户表 修改
 * @date 2018-09-28
 */
@Service
public class UpdateSysUserService extends AbstractService {

    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private UserRoleDao userRoleDao;


    protected Result doWork(IClientData data) throws Exception {

        String roleId = data.getParameter("roleId");

        SysUser newUser = (SysUser) data.getObject();

        //判空
        isNull(newUser);

        //根据id获取用户
        SysUser oldUser = getUserById(newUser);

        //验证用户名是否已经存在
        isUserName(newUser, oldUser);

        //验证手机号是否存在
        isPhone(newUser, oldUser);

        //验证邮箱是否存在
        isEmail(newUser, oldUser);

        //修改用户
        // 判断密码是否为md5,是则不修改
        if (Objects.equals(newUser.getPwd(), oldUser.getPwd())) {
            newUser.setPwd(null);
        }

        updateUser(newUser);

        //删除用户角色表
        removeUserRoleByUserId(newUser.getUserId());

        //给用户分配角色
        addUserRole(roleId, newUser.getUserId());

        return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), "修改成功");

    }

    /**
     * @param user
     * @Description 判空
     * @author 黄世杰
     * @下午12:54:25
     * @version 1.0
     */
    private void isNull(SysUser user) {
        ParamValidation.isNotNull(user.getUserId(), "主键不能为空");
        ParamValidation.isNotNull(user.getUserName(), "用户名不能为空");
        ParamValidation.isNotNull(user.getPhone(), "手机号不能为空");
        ParamValidation.isNotNull(user.getPwd(), "密码不能为空");
    }

    private SysUser getUserById(SysUser sysUser) {
        SysUser user = sysUserDao.selectSysUserById(sysUser.getUserId());
        if (user == null)
            throw new ValidataException("用户已被清理或用户不存在");
        return user;
    }

    /**
     * @param user
     * @Description 验证用户名是否存在
     * @author 黄世杰
     * @下午12:59:59
     * @version 1.0
     */
    private void isUserName(SysUser newUser, SysUser oldUser) {

        if (!oldUser.getPwd().equals(MD5.md5(newUser.getPwd())))
            newUser.setPwd(MD5.md5(newUser.getPwd()));
        else
            newUser.setPwd(null);

        //判空如果用户名没有修改  则不需要验证
        if (oldUser.getUserName().equals(newUser.getUserName()))
            return;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", newUser.getUserName());
        List<SysUser> users = sysUserDao.selectSysUserListAll(map);
        if (users.size() > 0)
            throw new ValidataException("用户名已经存在");
    }

    /**
     * @param user
     * @Description 验证手机号是否存在
     * @author 黄世杰
     * @下午12:59:45
     * @version 1.0
     */
    private void isPhone(SysUser newUser, SysUser oldUser) {
        //判空如果手机号没有修改  则不需要验证
        if (oldUser.getPhone().equals(newUser.getPhone()))
            return;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("phone", newUser.getPhone());
        List<SysUser> users = sysUserDao.selectSysUserListAll(map);
        if (users.size() > 0)
            throw new ValidataException("手机号已经存在");
    }

    /**
     * @param user
     * @Description 验证邮箱是否存在
     * @author 黄世杰
     * @下午12:59:29
     * @version 1.0
     */
    private void isEmail(SysUser newUser, SysUser oldUser) {
        if (StringUtils.isBlank(newUser.getEmail()))
            return;

        //判空如果邮箱没有修改  则不需要验证
        if (newUser.getEmail().equals(oldUser.getEmail()))
            return;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", newUser.getEmail());
        List<SysUser> users = sysUserDao.selectSysUserListAll(map);
        if (users.size() > 0)
            throw new ValidataException("邮箱已经存在");
    }


    /**
     * @param sysUser
     * @Description 添加用户
     * @author 黄世杰
     * @下午12:50:01
     * @version 1.0
     */
    private void updateUser(SysUser sysUser) {
        sysUserDao.updateSysUser(sysUser);
    }

    private void removeUserRoleByUserId(String userId) {
        userRoleDao.removeUserRoleByUserId(userId);
    }

    /**
     * @param roleId
     * @param userId
     * @Description 给用户分配角色
     * @author 黄世杰
     * @下午12:47:51
     * @version 1.0
     */
    private void addUserRole(String roleId, String userId) {
        UserRole userRole = new UserRole();
        userRole.setId(Tools.UUID());
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        userRoleDao.addUserRole(userRole);
    }

}
