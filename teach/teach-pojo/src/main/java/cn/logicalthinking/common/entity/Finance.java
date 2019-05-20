package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 系统的财务管理
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "系统的财务管理",description="系统的财务管理") 
public class Finance implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "学生姓名")
    private String studentName;
	@ApiModelProperty(value = "老师姓名")
    private String teacherName;
	@ApiModelProperty(value = "学生支付")
    private BigDecimal studentPay;
	@ApiModelProperty(value = "平台收益")
    private BigDecimal platformIncome;
	@ApiModelProperty(value = "老师收益")
    private BigDecimal teacherIncome;
	@ApiModelProperty(value = "老师提成比例")
    private BigDecimal teacherBonusRatio;

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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public BigDecimal getStudentPay() {
        return studentPay;
    }

    public void setStudentPay(BigDecimal studentPay) {
        this.studentPay = studentPay;
    }

    public BigDecimal getPlatformIncome() {
        return platformIncome;
    }

    public void setPlatformIncome(BigDecimal platformIncome) {
        this.platformIncome = platformIncome;
    }

    public BigDecimal getTeacherIncome() {
        return teacherIncome;
    }

    public void setTeacherIncome(BigDecimal teacherIncome) {
        this.teacherIncome = teacherIncome;
    }

    public BigDecimal getTeacherBonusRatio() {
        return teacherBonusRatio;
    }

    public void setTeacherBonusRatio(BigDecimal teacherBonusRatio) {
        this.teacherBonusRatio = teacherBonusRatio;
    }

    @Override
	public String toString() {
		return "Finance [id="+id+",createTime="+createTime+",updateTime="+updateTime+",studentName="+studentName+",teacherName="+teacherName+",studentPay="+studentPay+",platformIncome="+platformIncome+",teacherIncome="+teacherIncome+",teacherBonusRatio="+teacherBonusRatio+"]";
	}
    
}