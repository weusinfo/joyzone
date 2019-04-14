package com.joyzone.platform.module.admin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.MenuModel;
import com.joyzone.platform.core.service.PermissionService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private PermissionService permissionService;
	
	public R login(String userName, String password) {
		if(PublicUtil.isEmpty(userName)) return R.error("请输入用户名");
		if(PublicUtil.isEmpty(password)) return R.error("请输入密码");
		List<MenuModel> menus = permissionService.auth(userName, password);
		return R.ok(menus);
	}

}
