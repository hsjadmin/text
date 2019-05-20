package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 黄世杰
 * @version 1.0
 * @Description 角色权限表
 * @date 2018-09-26
 */
@ApiModel(value = "角色权限表", description = "角色权限表")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "菜单id")
    private String menuId;
    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RoleMenu [id=" + id + ",menuId=" + menuId + ",roleId=" + roleId + ",createTime=" + createTime + "]";
    }

}