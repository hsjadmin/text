package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description 课程大纲
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@ApiModel(value = "课程大纲",description="课程大纲") 
public class CourseOutline implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Integer id;
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	@ApiModelProperty(value = "修改时间")
    private String updateTime;
	@ApiModelProperty(value = "课程类型id")
    private Integer courseTypeId;
	@ApiModelProperty(value = "上课开始时间")
    private String startTime;
	@ApiModelProperty(value = "标题")
    private String title;
	@ApiModelProperty(value = "介绍")
    private String introduce;
	@ApiModelProperty(value = "是否已上，0未上课  1已上课 2上课中")
    private Integer status;
	@ApiModelProperty(value = "排序号")
    private Integer rank;
	@ApiModelProperty(value = "回看视频地址")
    private String videoUrl;
	@ApiModelProperty(value = "频道号")
    private String channelNo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
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

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
	public String toString() {
		return "CourseOutline [id="+id+",createTime="+createTime+",updateTime="+updateTime+",courseTypeId="+courseTypeId+",startTime="+startTime+",title="+title+",introduce="+introduce+",status="+status+",rank="+rank+",videoUrl="+videoUrl+"]";
	}
    
}