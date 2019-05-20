package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 优惠券
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "优惠券",description="优惠券") 
public class Coupon implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "学生id")
    private Integer studentId;
	@ApiModelProperty(value = "学生真实姓名")
    private String name;
	@ApiModelProperty(value = "用户名")
    private String userName;
	@ApiModelProperty(value = "优惠券使用开始时间")
    private String startTime;
	@ApiModelProperty(value = "有效时间")
    private String endTime;
	@ApiModelProperty(value = "优惠券名称")
    private String couponName;
	@ApiModelProperty(value = "满足条件金额")
    private BigDecimal satisfy;
	@ApiModelProperty(value = "折扣额度")
    private BigDecimal discount;
	@ApiModelProperty(value = "优惠券类型，0答疑，1课程")
    private Integer couponType;
	@ApiModelProperty(value = "0未使用1已使用")
    private Integer status;
    @ApiModelProperty(value = "优惠券标识，用于分组")
	private String identification;

    /**
     * 学生课程下单查询是否可用
     */
	private String available;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public BigDecimal getSatisfy() {
        return satisfy;
    }

    public void setSatisfy(BigDecimal satisfy) {
        this.satisfy = satisfy;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
	public String toString() {
		return "Coupon [id="+id+",createTime="+createTime+",updateTime="+updateTime+",studentId="+studentId+",name="+name+",userName="+userName+",startTime="+startTime+",endTime="+endTime+",couponName="+couponName+",satisfy="+satisfy+",discount="+discount+",couponType="+couponType+",status="+status+"]";
	}
    
}