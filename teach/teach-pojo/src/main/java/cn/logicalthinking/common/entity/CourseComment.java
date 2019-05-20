package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description 课程评论表
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "课程评论表",description="课程评论表") 
public class CourseComment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "课程id")
    private Integer courseId;
	@ApiModelProperty(value = "学生id")
    private Integer studentId;
	@ApiModelProperty(value = "学生头像")
    private String picture;
	@ApiModelProperty(value = "学生姓名")
    private String name;
	@ApiModelProperty(value = "评论类型 （0好评1中评2差评）")
    private Integer type;
	@ApiModelProperty(value = "几个星星")
    private Integer start;
	@ApiModelProperty(value = "评价内容")
    private String comment;
	@ApiModelProperty(value = "课程订单id")
    private Integer orderCourseId;

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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getOrderCourseId() {
        return orderCourseId;
    }

    public void setOrderCourseId(Integer orderCourseId) {
        this.orderCourseId = orderCourseId;
    }

    @Override
	public String toString() {
		return "CourseComment [id="+id+",createTime="+createTime+",updateTime="+updateTime+",courseId="+courseId+",studentId="+studentId+",picture="+picture+",name="+name+",type="+type+",start="+start+",comment="+comment+",orderCourseId="+orderCourseId+"]";
	}
    
}