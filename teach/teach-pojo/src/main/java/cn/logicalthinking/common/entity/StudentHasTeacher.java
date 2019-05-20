package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description 学生关注老师表
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "学生关注老师表",description="学生关注老师表") 
public class StudentHasTeacher implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "学生id")
    private Integer studentId;
	@ApiModelProperty(value = "老师id")
    private Integer teacherId;
	@ApiModelProperty(value = "创建时间")
    private String createTime;

	private Teacher teacher;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
	public String toString() {
		return "StudentHasTeacher [studentId="+studentId+",teacherId="+teacherId+",createTime="+createTime+"]";
	}
    
}