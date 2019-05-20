package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 疑难解答订单
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "疑难解答订单",description="疑难解答订单") 
public class OrderQuestion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "学生id")
    private Integer studentId;
	@ApiModelProperty(value = "老师id")
    private Integer teacherId;
	@ApiModelProperty(value = "学生姓名")
    private String studentName;
	@ApiModelProperty(value = "老师姓名")
    private String teacherName;
	@ApiModelProperty(value = "问题图片")
    private String questionImg;
	@ApiModelProperty(value = "审核状态(0待审核 1解疑中  2待支付 3已完成  4未通过 5已取消)")
    private Integer status;
	@ApiModelProperty(value = "价格")
    private BigDecimal price;
	@ApiModelProperty(value = "是否使用优惠券（0否  1是）")
    private Integer isCoupon;
	@ApiModelProperty(value = "优惠券金额")
    private BigDecimal couponPrice;
	@ApiModelProperty(value = "是否支付(0否 1是）")
    private Integer isPay;
	@ApiModelProperty(value = "支付类型(0微信 1代付 2余额支付）")
    private Integer payType;
	@ApiModelProperty(value = "收货手机号")
    private String phone;
	@ApiModelProperty(value = "收货详细地址")
    private String address;
	@ApiModelProperty(value = "收货区域  （省市区）")
    private String area;
	@ApiModelProperty(value = "答疑时长(分)")
    private String answeringTime;
	@ApiModelProperty(value = "是否评价（0否，1是）")
    private String apprised;
	@ApiModelProperty(value = "是否评价（0否，1是）")
    private BigDecimal truePrice;
	@ApiModelProperty(value = "年级")
    private String grade;

    public BigDecimal getTruePrice() {
        return truePrice;
    }

    public void setTruePrice(BigDecimal truePrice) {
        this.truePrice = truePrice;
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

    public String getQuestionImg() {
        return questionImg;
    }

    public void setQuestionImg(String questionImg) {
        this.questionImg = questionImg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAnsweringTime() {
        return answeringTime;
    }

    public void setAnsweringTime(String answeringTime) {
        this.answeringTime = answeringTime;
    }

    public String getApprised() {
        return apprised;
    }

    public void setApprised(String apprised) {
        this.apprised = apprised;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
	public String toString() {
		return "OrderQuestion [id="+id+",createTime="+createTime+",updateTime="+updateTime+",studentId="+studentId+",teacherId="+teacherId+",studentName="+studentName+",teacherName="+teacherName+",questionImg="+questionImg+",status="+status+",price="+price+",isCoupon="+isCoupon+",couponPrice="+couponPrice+",isPay="+isPay+",payType="+payType+",phone="+phone+",address="+address+",area="+area+",answeringTime="+answeringTime+",grade="+grade+"]";
	}
    
}