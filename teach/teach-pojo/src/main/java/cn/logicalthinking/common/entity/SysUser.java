package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author 黄世杰
 * @version 1.0
 * @Description 用户表
 * @date 2018-09-28
 */
@ApiModel(value = "用户表", description = "用户表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public class Model1{}
    public class Model2{}
    
    @ApiModelProperty(value = "主键")
    private String userId;
    @ApiModelProperty(value = "登录名")
    private String userName;
    @ApiModelProperty(value = "真实姓名")
    private String trueName;
    @ApiModelProperty(value = "性别(0男1女2保密)")
    private String sex;
    @ApiModelProperty(value = "电子邮箱")
    private String email;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "密码")
    private String pwd;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "登录时间")
    private String loginTime;
    @ApiModelProperty(value = "最后一次登录时间")
    private String lastLoginTime;
    @ApiModelProperty(value = "用户状态(0有效  1无效)")
    private String state;
    @ApiModelProperty(value = "用户类型(0前台用户1后台用户)")
    private String type;
    @ApiModelProperty(value = "头像路径")
    private String picturePath;
    @ApiModelProperty(value = "所在地")
    private String address;
    
	private List<SysRole> roleList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "SysUser [userId=" + userId + ", userName=" + userName
				+ ", trueName=" + trueName + ", sex=" + sex + ", email="
				+ email + ", phone=" + phone + ", pwd=" + pwd + ", createTime="
				+ createTime + ", loginTime=" + loginTime + ", lastLoginTime="
				+ lastLoginTime + ", state=" + state + ", type=" + type
				+ ", picturePath=" + picturePath + ", address=" + address
				+ ", roleList=" + roleList + "]";
	}

}