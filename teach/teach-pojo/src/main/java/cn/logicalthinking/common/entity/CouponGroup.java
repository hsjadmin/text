package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 优惠券组
 * @author 黄世杰
 * @date 2019-01-19
 * @version  1.0
 */
@ApiModel(value = "优惠券组",description="优惠券组") 
public class CouponGroup implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
    private String id;
	@ApiModelProperty(value = "优惠券使用开始时间")
    private String startTime;
	@ApiModelProperty(value = "发布时间")
    private String createTime;
	@ApiModelProperty(value = "有效时间")
    private String endTime;
	@ApiModelProperty(value = "优惠券名称")
    private String couponName;
	@ApiModelProperty(value = "")
    private BigDecimal satisfy;
	@ApiModelProperty(value = "")
    private BigDecimal discount;
	@ApiModelProperty(value = "优惠券类型，0答疑，1课程")
    private Integer couponType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
	public String toString() {
		return "CouponGroup [id="+id+",startTime="+startTime+",endTime="+endTime+",couponName="+couponName+",satisfy="+satisfy+",discount="+discount+",couponType="+couponType+"]";
	}
    
}