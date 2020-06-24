package CRM.entity;

import java.util.List;

public class Menu {
	private int id;//菜单的id
	private String text;//菜单的名字
	private String iconCls;//菜单的图标
	private int pid;//菜单的父菜单的id，如果是一级菜单，为0
	private String href;//菜单的名称，用于在点击菜单时跳转到指定的页面
	private List<Menu> children;//用于存放子菜单，父菜单由于没有set，属性是null，所以打印json时不会打出来
	private Attributes attributes;//用来封装href以便让easyUI的Node可以接收到
	private boolean checked;
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	
}
