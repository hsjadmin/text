package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description 学生充值订单
 * @author 黄世杰
 * @date 2018-12-29
 * @version  1.0
 */
@ApiModel(value = "学生充值订单",description="学生充值订单") 
public class OrderRecharge implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
    private String id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "学生id")
    private Integer studentId;
	@ApiModelProperty(value = "充值金额")
    private Double amount;
	@ApiModelProperty(value = "充值状态（0预支付，1支付中，2支付成功，3支付失败，4关闭订单）")
    private String status;
	@ApiModelProperty(value = "支付方式（0自己支付，1好友代付）")
    private String payment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
    
    @Override
	public String toString() {
		return "OrderRecharge [id="+id+",createTime="+createTime+",updateTime="+updateTime+",studentId="+studentId+",amount="+amount+",status="+status+",payment="+payment+"]";
	}
    
}