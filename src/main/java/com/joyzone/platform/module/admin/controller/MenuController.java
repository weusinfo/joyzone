package com.joyzone.platform.module.admin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.MenuModel;
import com.joyzone.platform.core.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/menus")
@Api(tags="1.0",value="后台用户菜单清单")
public class MenuController {
	
	@Autowired
	private MenuService menuService;

	@ApiOperation(notes="根据系统用户ID获取该用户菜单项", value = "获取用户菜单项")
	@PostMapping("/list")
	public R list(@RequestParam("userId") Long userId) {
		List<MenuModel> menus = menuService.list(userId);
		return R.ok(menus);
	}
	
	@ApiOperation(notes="给用户分配菜单项", value = "分配用户菜单项")
	@PostMapping("/update")
	public R update() {
		menuService.update();
		return R.ok();
	}
}
