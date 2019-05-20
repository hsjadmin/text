package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 黄世杰
 * @version 1.0
 * @Description 系统日志表
 * @date 2018-09-28
 */
@ApiModel(value = "系统日志表", description = "系统日志表")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String logid;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "操作名称")
    private String operateName;
    @ApiModelProperty(value = "操作地址")
    private String operateUrl;
    @ApiModelProperty(value = "操作人token")
    private String token;
    @ApiModelProperty(value = "ip地址")
    private String ip;
    @ApiModelProperty(value = "端口")
    private String port;
    @ApiModelProperty(value = "客户端类型(0pc 1android 2ios)")
    private String customerType;
    @ApiModelProperty(value = "类型（1订单操作 2基础信息操作  3用户操作）")
    private String type;
    @ApiModelProperty(value = "操作时间")
    private String createTime;
    @ApiModelProperty(value = "过期状态（0有效1失效）")
    private String loseStatus;
    @ApiModelProperty(value = "操作类型（1新增 2修改 3删除 4确认 5取消 6审核 7取消审核 8启用  9禁用）")
    private String opType;

    SysUser sysUser;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getOperateUrl() {
        return operateUrl;
    }

    public void setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoseStatus() {
        return loseStatus;
    }

    public void setLoseStatus(String loseStatus) {
        this.loseStatus = loseStatus;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    @Override
    public String toString() {
        return "SysLog [logid=" + logid + ",userId=" + userId + ",operateName=" + operateName + ",operateUrl=" + operateUrl + ",token=" + token + ",ip=" + ip + ",port=" + port + ",customerType=" + customerType + ",type=" + type + ",createTime=" + createTime + ",loseStatus=" + loseStatus + ",opType=" + opType + "]";
    }

}