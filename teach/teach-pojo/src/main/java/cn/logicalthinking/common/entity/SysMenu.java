package cn.logicalthinking.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author 黄世杰
 * @version 1.0
 * @Description 功能权限表
 * @date 2018-09-28
 */
@ApiModel(value = "功能权限表", description = "功能权限表")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "菜单名")
    private String name;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "访问地址")
    private String href;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "排序号")
    private String rank;
    @ApiModelProperty(value = "类型（0功能菜单1一级菜单2二级菜单）")
    private String type;
    @ApiModelProperty(value = "有效状态（0无效1有效）")
    private String state;
    @ApiModelProperty(value = "父id")
    private String pId;
    @ApiModelProperty(value = "创建时间")
    private String crateTime;
    
    
    private boolean spread;
    
    private String title;
    
    private List<SysMenu> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(String crateTime) {
        this.crateTime = crateTime;
    }

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "SysMenu [id=" + id + ", name=" + name + ", icon=" + icon
				+ ", href=" + href + ", remark=" + remark + ", rank=" + rank
				+ ", type=" + type + ", state=" + state + ", pId=" + pId
				+ ", crateTime=" + crateTime + ", spread=" + spread
				+ ", title=" + title + ", children=" + children + "]";
	}

}