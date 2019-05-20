package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 黄世杰
 * @version 1.0
 * @Description 用户角色表
 * @date 2018-09-26
 */
@ApiModel(value = "用户角色表", description = "用户角色表")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "角色id")
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole [id=" + id + ",userId=" + userId + ",roleId=" + roleId + "]";
    }

}