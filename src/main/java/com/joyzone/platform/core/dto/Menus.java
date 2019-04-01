package com.joyzone.platform.core.dto;

import java.util.List;

public class Menus {

	private String group;
	private List<MenuItem> groupItem;

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroup() {
		return group;
	}

	public void setGroupItem(List<MenuItem> groupItem) {
		this.groupItem = groupItem;
	}

	public List<MenuItem> getGroupItem() {
		return groupItem;
	}

}
