package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description 学生的收货地址表
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "学生的收货地址表",description="学生的收货地址表") 
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "学生id")
    private Integer studentId;
	@ApiModelProperty(value = "姓名")
    private String name;
	@ApiModelProperty(value = "手机号")
    private String phone;
	@ApiModelProperty(value = "所在地区(省市区)")
    private String area;
	@ApiModelProperty(value = "详细地址")
    private String address;
	@ApiModelProperty(value = "是否为默认地址（0否，1是）")
    private String defaulting;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDefaulting() {
        return defaulting;
    }

    public void setDefaulting(String defaulting) {
        this.defaulting = defaulting;
    }

    @Override
	public String toString() {
		return "Address [id="+id+",createTime="+createTime+",updateTime="+updateTime+",studentId="+studentId+",name="+name+",phone="+phone+",area="+area+",address="+address+"]";
	}
    
}