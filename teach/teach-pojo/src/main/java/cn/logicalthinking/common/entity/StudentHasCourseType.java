package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 学生订阅课程表
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "学生订阅课程表",description="学生订阅课程表") 
public class StudentHasCourseType implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "学生id")
    private Integer studentId;
	@ApiModelProperty(value = "课程类型id")
    private Integer courseTypeId;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "课程id")
    private Integer courseId;

	private List<Teacher> teacherList;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
	public String toString() {
		return "StudentHasCourseType [studentId="+studentId+",courseTypeId="+courseTypeId+",createTime="+createTime+",courseId="+courseId+"]";
	}
    
}