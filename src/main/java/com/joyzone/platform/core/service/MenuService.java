package com.joyzone.platform.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.MenuItem;
import com.joyzone.platform.core.dto.Menus;
import com.joyzone.platform.core.mapper.MenuMapper;
import com.joyzone.platform.core.model.MenuModel;

@Service
@Transactional
public class MenuService extends BaseService<MenuModel> {
	
	@Autowired
	private MenuMapper menuMapper;
	
	public List<Menus> list(Long userId){
		List<MenuModel> userMenus = menuMapper.getMenusByUserId(userId);
		List<Menus> menus = getMenus(userMenus);
		if(PublicUtil.isEmpty(menus)) throw new JZException("没有权限");
		return menus;
	}
	
	public void update(Long userId, Long[] ids) {
		if(PublicUtil.isEmpty(ids)) throw new JZException("请至少选择一个权限");
		menuMapper.deleteMenus(userId);
		menuMapper.addMenus(userId, ids);
	}
	
	/**
	 * [{
		"group":"系统设置",
		"groupItem":[{
			"name":"新增系统用户",
			"id":2
			},
			{
			"name":"系统用户清单",
			"id":3
			},
			{
			"name":"系统角色管理",
			"id":4
			}]
}]
	 */
	public List<Menus> getAllMenus(){
		
		List<MenuModel> allMenus = menuMapper.getAllMenus();
		List<Menus> results = getMenus(allMenus);
		if(PublicUtil.isEmpty(results)) throw new JZException("没有有效菜单");
		return results;
	}

	private List<Menus> getMenus(List<MenuModel> menuModels){
		List<Menus> result = new ArrayList<Menus>();
		if(PublicUtil.isNotEmpty(menuModels)) {
			Map<String, List<MenuModel>> map = new HashMap<String,List<MenuModel>>();
			menuModels.forEach(menu -> {
				String desc = menu.getDescription();
				if(map.containsKey(desc)) {
					map.get(desc).add(menu);
				}else {
					List<MenuModel> list = new ArrayList<MenuModel>();
					list.add(menu);
					map.put(desc, list);
				}
			});
			map.keySet().forEach(key ->{
				List<MenuModel> menuItems = map.get(key);
				Menus menus = new Menus();
				menus.setGroup(key);
				List<MenuItem> list = new ArrayList<MenuItem>();
				menuItems.forEach(item ->{
					MenuItem menuItem = new MenuItem();
					menuItem.setName(item.getName());
					menuItem.setId(item.getId());
					menuItem.setPath(item.getUrl());
					list.add(menuItem);
				});
				menus.setGroupItem(list);
				result.add(menus);
			});
		}
		return result;
	}
}
