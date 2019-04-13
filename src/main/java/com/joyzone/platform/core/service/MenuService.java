package com.joyzone.platform.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.MenuItem;
import com.joyzone.platform.core.dto.Menus;
import com.joyzone.platform.core.mapper.MenuMapper;
import com.joyzone.platform.core.model.MenuModel;

@Service
public class MenuService extends BaseService<MenuModel> {
	
	@Autowired
	private MenuMapper menuMapper;
	
	public List<MenuModel> list(Long userId){
		List<MenuModel> menus = menuMapper.getMenus(userId);
		if(PublicUtil.isNotEmpty(menus)) throw new JZException("没有权限");
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
		List<Menus> result = new ArrayList<Menus>();
		List<MenuModel> allMenus = menuMapper.getAllMenus();
		if(PublicUtil.isNotEmpty(allMenus)) {
			Map<String, List<MenuModel>> map = new HashMap<String,List<MenuModel>>();
			allMenus.forEach(menu -> {
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
				});
				menus.setGroupItem(list);
				result.add(menus);
			});
		}
		if(PublicUtil.isEmpty(result)) throw new JZException("没有有效菜单");
		return result;
	}

}
