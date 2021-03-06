package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description 营销素材-学生端首页
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "营销素材-学生端首页",description="营销素材-学生端首页") 
public class MarketingBanner implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "课程名")
    private String name;
	@ApiModelProperty(value = "资料链接")
    private String url;
	@ApiModelProperty(value = "内容")
    private String content;
	@ApiModelProperty(value = "状态,0上架  1下架")
    private Integer status;
	@ApiModelProperty(value = "观看人数")
    private Integer number;
	@ApiModelProperty(value = "轮播图")
    private String coverMap;
	@ApiModelProperty(value = "是否是跳链接（0否  1是）")
    private Integer isHref;
	@ApiModelProperty(value = "素材类型(0:轮播 1:视频)")
    private Integer materialType;
	@ApiModelProperty(value = "视频地址")
    private String datum;
    @ApiModelProperty(value = "发布人")
    private String userName;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCoverMap() {
        return coverMap;
    }

    public void setCoverMap(String coverMap) {
        this.coverMap = coverMap;
    }

    public Integer getIsHref() {
        return isHref;
    }

    public void setIsHref(Integer isHref) {
        this.isHref = isHref;
    }

    public Integer getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
	public String toString() {
		return "MarketingBanner [id="+id+",createTime="+createTime+",updateTime="+updateTime+",name="+name+",url="+url+",content="+content+",status="+status+",number="+number+",coverMap="+coverMap+",isHref="+isHref+",materialType="+materialType+",datum="+datum+"]";
	}
    
}