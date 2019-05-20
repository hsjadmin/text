package cn.logicalthinking.common.base.entity;
/**
 * 和分配权限映射实体   无数据表对应
 * @author Administrator
 *
 */
public class MenuZ_Tree {
	private String id;
	private String pId;
	private String name;
	private String icon;
	private boolean open;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
