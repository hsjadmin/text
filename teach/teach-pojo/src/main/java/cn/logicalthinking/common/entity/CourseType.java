package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 黄世杰
 * @version 1.0
 * @Description 课程类型表，即大班课小班课一对一等课程
 * @date 2018-12-19
 */
@ApiModel(value = "课程类型表，即大班课小班课一对一等课程", description = "课程类型表，即大班课小班课一对一等课程")
public class CourseType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "修改时间")
    private String updateTime;
    @ApiModelProperty(value = "课程id")
    private Integer courseId;
    @ApiModelProperty(value = "课程类型（0 一对一，1小班课，2大班课）")
    private Integer courseType;
    @ApiModelProperty(value = "原价，缩写")
    private BigDecimal orig;
    @ApiModelProperty(value = "优惠价")
    private BigDecimal discount;
    @ApiModelProperty(value = "课时")
    private String periods;
    @ApiModelProperty(value = "课程时长，分钟")
    private Integer duration;
    @ApiModelProperty(value = "开班时间")
    private String startTime;
    @ApiModelProperty(value = "上课时间")
    private String courseTime;
    @ApiModelProperty(value = "可报名总人数（对于课程类型）")
    private Integer quantity;
    @ApiModelProperty(value = "已报名总人数")
    private Integer enrolment;
    @ApiModelProperty(value = "上课频率（每天：d；每周1,2,5：w_1,2,5）")
    private String classFrequency;
    @ApiModelProperty(value = "学生上完课依据（0否，1是），是否完成该课程类型下所有章节")
    private Integer isFinish;
    @ApiModelProperty(value = "老师提成")
    private BigDecimal commission;

    private Course course;
    private Teacher teacher;

    /**
     * 添加课程使用
     */
    private List<CourseOutline> courseOutlineList;

    public List<CourseOutline> getCourseOutlineList() {
        return courseOutlineList;
    }

    public void setCourseOutlineList(List<CourseOutline> courseOutlineList) {
        this.courseOutlineList = courseOutlineList;
    }

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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getEnrolment() {
        return enrolment;
    }

    public void setEnrolment(Integer enrolment) {
        this.enrolment = enrolment;
    }

    public String getClassFrequency() {
        return classFrequency;
    }

    public void setClassFrequency(String classFrequency) {
        this.classFrequency = classFrequency;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "CourseType{" +
                "id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseType='" + courseType + '\'' +
                ", orig=" + orig +
                ", discount=" + discount +
                ", periods='" + periods + '\'' +
                ", duration='" + duration + '\'' +
                ", startTime='" + startTime + '\'' +
                ", courseTime='" + courseTime + '\'' +
                ", quantity='" + quantity + '\'' +
                ", enrolment='" + enrolment + '\'' +
                ", classFrequency='" + classFrequency + '\'' +
                ", isFinish='" + isFinish + '\'' +
                ", commission='" + commission + '\'' +
                '}';
    }
}