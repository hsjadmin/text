package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 黄世杰
 * @version 1.0
 * @Description 课程订单
 * @date 2018-12-19
 */
@ApiModel(value = "课程订单", description = "课程订单")
public class OrderCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "修改时间")
    private String updateTime;
    @ApiModelProperty(value = "学生姓名")
    private String studentName;
    @ApiModelProperty(value = "性别（0男 1女）")
    private Integer gender;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "原价，缩写")
    private BigDecimal orig;
    @ApiModelProperty(value = "优惠价")
    private BigDecimal discount;
    @ApiModelProperty(value = "课时")
    private String periods;
    @ApiModelProperty(value = "课程时长，分钟")
    private Integer duration;
    @ApiModelProperty(value = "上课时间")
    private String courseTime;
    @ApiModelProperty(value = "老师姓名")
    private String teacherName;
    @ApiModelProperty(value = "是否使用优惠券(0否  1是)")
    private Integer isCoupon;
    @ApiModelProperty(value = "优惠券金额")
    private BigDecimal couponPrice;
    @ApiModelProperty(value = "教师提成")
    private BigDecimal teacherBonus;
    @ApiModelProperty(value = "学生id")
    private Integer studentId;
    @ApiModelProperty(value = "老师id")
    private Integer teacherId;
    @ApiModelProperty(value = "课程id")
    private Integer courseId;
    @ApiModelProperty(value = "课程类型id")
    private Integer courseTypeId;
    @ApiModelProperty(value = "是否已评论（0否，1是）")
    private String apprised;
    @ApiModelProperty(value = "支付金额")
    private BigDecimal amount;
    @ApiModelProperty(value = "订单状态（0创建，1支付中，2支付成功，3支付失败，4超时关闭，5取消订单，6退款）")
    private Integer status;
    @ApiModelProperty(value = "课程标题")
    private String courseTitle;
    @ApiModelProperty(value = "课程信息")
    private String courseInfo;
    @ApiModelProperty(value = "课程开始时间")
    private String courseStartTime;
    @ApiModelProperty(value = "上课频率")
    private String courseClassFrequency;
    @ApiModelProperty(value = "优惠券id")
    private Integer couponId;
    @ApiModelProperty(value = "学生年级")
    private String grade;

    private CourseType courseType;

    private Course course;

    private Teacher teacher;

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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getOrig() {
        return orig;
    }

    public void setOrig(BigDecimal orig) {
        this.orig = orig;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getIsCoupon() {
        return isCoupon;
    }

    public void setIsCoupon(Integer isCoupon) {
        this.isCoupon = isCoupon;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getTeacherBonus() {
        return teacherBonus;
    }

    public void setTeacherBonus(BigDecimal teacherBonus) {
        this.teacherBonus = teacherBonus;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getApprised() {
        return apprised;
    }

    public void setApprised(String apprised) {
        this.apprised = apprised;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getCourseStartTime() {
        return courseStartTime;
    }

    public void setCourseStartTime(String courseStartTime) {
        this.courseStartTime = courseStartTime;
    }

    public String getCourseClassFrequency() {
        return courseClassFrequency;
    }

    public void setCourseClassFrequency(String courseClassFrequency) {
        this.courseClassFrequency = courseClassFrequency;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "OrderCourse [id=" + id + ",createTime=" + createTime + ",updateTime=" + updateTime + ",studentName=" + studentName + ",gender=" + gender + ",address=" + address + ",phone=" + phone + ",orig=" + orig + ",discount=" + discount + ",periods=" + periods + ",duration=" + duration + ",courseTime=" + courseTime + ",teacherName=" + teacherName + ",isCoupon=" + isCoupon + ",couponPrice=" + couponPrice + ",teacherBonus=" + teacherBonus + ",studentId=" + studentId + ",teacherId=" + teacherId + ",courseId=" + courseId + ",courseTypeId=" + courseTypeId + "+couponId="+couponId+"grade="+grade+"]";
    }

}