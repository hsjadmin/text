package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 老师表
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "老师表",description="老师表") 
public class Teacher implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "真实姓名")
    private String name;
	@ApiModelProperty(value = "年龄")
    private String age;
	@ApiModelProperty(value = "")
    private String teachercol;
	@ApiModelProperty(value = "性别（0男 1女  2保密）")
    private String gender;
	@ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "级别（小学 初中 高中）")
    private String level;
	@ApiModelProperty(value = "年级")
    private String grade;
	@ApiModelProperty(value = "科目")
    private String subject;
	@ApiModelProperty(value = "授课年龄")
    private String experience;
    @ApiModelProperty(value = "身份证号")
    private String idCard;
    @ApiModelProperty(value = "头像")
    private String picture;
    @ApiModelProperty(value = "教师资格证")
    private String cardImg;
    @ApiModelProperty(value = "审核状态，0未审核1待审核2通过3不通过")
    private String examine;
    @ApiModelProperty(value = "不通过理由")
    private String reason;
    @ApiModelProperty(value = "所属区域（省市区）")
    private String region;
    @ApiModelProperty(value = "详细地址")
    private String address;
    @ApiModelProperty(value = "总得评分数，用于计算显示教师的总评分")
    private BigDecimal starCount;
    @ApiModelProperty(value = "总被评分次数，用于计算显示教师的总评分")
    private Integer starTimes;
    @ApiModelProperty(value = "用户名")
    private String userName;
	@ApiModelProperty(value = "余额")
    private BigDecimal balance;
	@ApiModelProperty(value = "状态（0启用 1禁用）")
    private Integer status;
    @ApiModelProperty(value = "教学经历")
    private String teachingExperience;
    @ApiModelProperty(value = "荣誉证书")
    private String certificateOfHonor;
    @ApiModelProperty(value = "个人介绍")
    private String introduce;
    @ApiModelProperty(value = "是否显示（0显示  1不显示）")
    private Integer isShow;
    @ApiModelProperty(value = "收费百分比")
    private BigDecimal charges;
    @ApiModelProperty(value = "超时收费设置（元/分钟）")
    private BigDecimal chargeSettings;
    @ApiModelProperty(value = "微信用户id")
    private String openId;
    @ApiModelProperty(value = "直播推流地址")
    private String liveRoom;
    @ApiModelProperty(value = "支付密码")
    private String payPwd;

    @ApiModelProperty(value = "起步价")
    private BigDecimal startingPrice;

    @ApiModelProperty(value = "疑难是否在线(0否1解疑中2是)")
    private int isOnline;

    /**
     * 课程是否约满
     */
    private String fully;
    /**
     * 平均评分
     */
    private String star;

    private Course course;

    private OrderQuestion orderQuestion;

    /*未完成课程集合*/
    private List<Course> courseList;

    /*已完成课程集合*/
    private List<Course> courses;

    private List<OrderQuestion> orderQuestionList;

    private List<FinanceTeacher> financeTeachers;

    public List<FinanceTeacher> getFinanceTeachers() {
        return financeTeachers;
    }

    public void setFinanceTeachers(List<FinanceTeacher> financeTeachers) {
        this.financeTeachers = financeTeachers;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTeachercol() {
        return teachercol;
    }

    public void setTeachercol(String teachercol) {
        this.teachercol = teachercol;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getStarCount() {
        return starCount;
    }

    public void setStarCount(BigDecimal starCount) {
        this.starCount = starCount;
    }

    public Integer getStarTimes() {
        return starTimes;
    }

    public void setStarTimes(Integer starTimes) {
        this.starTimes = starTimes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getTeachingExperience() {
        return teachingExperience;
    }

    public void setTeachingExperience(String teachingExperience) {
        this.teachingExperience = teachingExperience;
    }

    public String getCertificateOfHonor() {
        return certificateOfHonor;
    }

    public void setCertificateOfHonor(String certificateOfHonor) {
        this.certificateOfHonor = certificateOfHonor;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public BigDecimal getCharges() {
        return charges;
    }

    public void setCharges(BigDecimal charges) {
        this.charges = charges;
    }

    public BigDecimal getChargeSettings() {
        return chargeSettings;
    }

    public void setChargeSettings(BigDecimal chargeSettings) {
        this.chargeSettings = chargeSettings;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getLiveRoom() {
        return liveRoom;
    }

    public void setLiveRoom(String liveRoom) {
        this.liveRoom = liveRoom;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public OrderQuestion getOrderQuestion() {
        return orderQuestion;
    }

    public void setOrderQuestion(OrderQuestion orderQuestion) {
        this.orderQuestion = orderQuestion;
    }

    public String getFully() {
        return fully;
    }

    public void setFully(String fully) {
        this.fully = fully;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<OrderQuestion> getOrderQuestionList() {
        return orderQuestionList;
    }

    public void setOrderQuestionList(List<OrderQuestion> orderQuestionList) {
        this.orderQuestionList = orderQuestionList;
    }

    public String getStar() {
        return this.star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", teachercol='" + teachercol + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", level='" + level + '\'' +
                ", grade='" + grade + '\'' +
                ", subject='" + subject + '\'' +
                ", experience='" + experience + '\'' +
                ", idCard='" + idCard + '\'' +
                ", picture='" + picture + '\'' +
                ", cardImg='" + cardImg + '\'' +
                ", examine='" + examine + '\'' +
                ", reason='" + reason + '\'' +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", starCount=" + starCount +
                ", starTimes='" + starTimes + '\'' +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                ", teachingExperience='" + teachingExperience + '\'' +
                ", certificateOfHonor='" + certificateOfHonor + '\'' +
                ", introduce='" + introduce + '\'' +
                ", isShow='" + isShow + '\'' +
                ", charges='" + charges + '\'' +
                ", chargeSettings=" + chargeSettings +
                ", openId='" + openId + '\'' +
                ", liveUrl='" + liveRoom + '\'' +
                '}';
    }
}