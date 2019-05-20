package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 课程表
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "课程表",description="课程表") 
public class Course implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "老师id")
    private Integer teacherId;
	@ApiModelProperty(value = "课程名")
    private String name;
	@ApiModelProperty(value = "年级")
    private String grade;
	@ApiModelProperty(value = "科目")
    private String subject;
	@ApiModelProperty(value = "简介")
    private String introduce;
	@ApiModelProperty(value = "状态(0有效  1无效)")
    private Integer status;
	@ApiModelProperty(value = "科目类型（小学 初中  高中）")
    private String courseType;
	@ApiModelProperty(value = "是否显示（0显示  1不显示）")
    private Integer isShow;
	@ApiModelProperty(value = "是否正在直播（0否，1是）")
    private Integer liveStatus;
	@ApiModelProperty(value = "老师上完课依据（0否，1是），课程所有类型及章节是否完成")
    private Integer isFinish;
    @ApiModelProperty(value = "级别")
    private String level;

    /**
     * 添加课程使用
     */
    private List<CourseType> courseTypeList;


    /*课程类别*/
    private CourseType courseTypeInfo;
    /*课程大纲*/
    private List<CourseOutline> courseOutlines;
    /*课程评价*/
    private List<CourseComment> courseComments;

    public List<CourseType> getCourseTypeList() {
        return courseTypeList;
    }

    public void setCourseTypeList(List<CourseType> courseTypeList) {
        this.courseTypeList = courseTypeList;
    }

    public CourseType getCourseTypeInfo() {
        return courseTypeInfo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setCourseTypeInfo(CourseType courseTypeInfo) {
        this.courseTypeInfo = courseTypeInfo;
    }

    public List<CourseOutline> getCourseOutlines() {
        return courseOutlines;
    }

    public void setCourseOutlines(List<CourseOutline> courseOutlines) {
        this.courseOutlines = courseOutlines;
    }

    public List<CourseComment> getCourseComments() {
        return courseComments;
    }

    public void setCourseComments(List<CourseComment> courseComments) {
        this.courseComments = courseComments;
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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(Integer liveStatus) {
        this.liveStatus = liveStatus;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    @Override
	public String toString() {
		return "Course [id="+id+",createTime="+createTime+",updateTime="+updateTime+",teacherId="+teacherId+",name="+name+",grade="+grade+",subject="+subject+",introduce="+introduce+",status="+status+",courseType="+courseType+",isShow="+isShow+",liveStatus="+liveStatus+",isFinish="+isFinish+"]";
	}
    
}