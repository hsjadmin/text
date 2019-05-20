package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 学生表
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "学生表",description="学生表") 
public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "真实姓名")
    private String name;
	@ApiModelProperty(value = "性别（0男 1女  2保密）")
    private Integer gender;
	@ApiModelProperty(value = "地区（省市区）")
    private String region;
	@ApiModelProperty(value = "手机号")
    private String phone;
	@ApiModelProperty(value = "年龄")
    private Integer age;
	@ApiModelProperty(value = "年级，直接存一年级二年级")
    private String grade;
	@ApiModelProperty(value = "头像路径")
    private String picture;
	@ApiModelProperty(value = "用户名 ")
    private String userName;
	@ApiModelProperty(value = "详细地址")
    private String address;
	@ApiModelProperty(value = "余额")
    private BigDecimal balance;
	@ApiModelProperty(value = "用户状态 （0启用 1禁用）")
    private Integer status;
	@ApiModelProperty(value = "身份证")
    private String idCard;
	@ApiModelProperty(value = "微信用户id")
    private String openId;
	@ApiModelProperty(value = "支付密码")
    private String pin;
	@ApiModelProperty(value = "是否设置支付密码（0否，1是）")
    private String hasPin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getHasPin() {
        return hasPin;
    }

    public void setHasPin(String hasPin) {
        this.hasPin = hasPin;
    }

    @Override
	public String toString() {
		return "Student [id="+id+",createTime="+createTime+",updateTime="+updateTime+",name="+name+",gender="+gender+",region="+region+",phone="+phone+",age="+age+",grade="+grade+",picture="+picture+",userName="+userName+",address="+address+",balance="+balance+",status="+status+",idCard="+idCard+",openId="+openId+"]";
	}
    
}