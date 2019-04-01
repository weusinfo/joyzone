package com.joyzone.platform.core.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.MenuMapper;
import com.joyzone.platform.core.model.MenuModel;

public class MenuService extends BaseService<MenuModel> {
	
	@Autowired
	private MenuMapper menuMapper;
	
	public List<MenuModel> list(Long userId){
		List<MenuModel> menus = menuMapper.getMenus(userId);
		if(PublicUtil.isNotEmpty(menus)) throw new JZException("没有权限");
		return menus;
	}
	
	public void update() {
		
	}

}
