package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * @author 黄世杰
 * @version 1.0
 * @Description 角色表
 * @date 2018-09-28
 */
@ApiModel(value = "角色表", description = "角色表")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public class Model1{}
    public class Model2{}
    
    @ApiModelProperty(value = "主键")
    private String roleId;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "角色唯一标识")
    private String roleMark;
    @ApiModelProperty(value = "角色状态(0有效1无效)")
    private String state;
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleMark() {
        return roleMark;
    }

    public void setRoleMark(String roleMark) {
        this.roleMark = roleMark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysRole [roleId=" + roleId + ",roleName=" + roleName + ",roleMark=" + roleMark + ",state=" + state + ",createTime=" + createTime + "]";
    }

}