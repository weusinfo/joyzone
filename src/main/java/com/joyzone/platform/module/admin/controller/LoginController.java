package com.joyzone.platform.module.admin.controller;

import java.util.List;

import com.joyzone.platform.core.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.Menus;
import com.joyzone.platform.core.model.MenuModel;
import com.joyzone.platform.core.service.PermissionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/login")
@Api(tags="后台用户登陆",description="LoginController")
public class LoginController {
	
	@Autowired
	private PermissionService permissionService;
	
	@ApiOperation("后台用户登陆")
	@PostMapping("/auth")
	public R auth(String userName, String password) {
		if(PublicUtil.isEmpty(userName)) return R.error("请输入用户名");
		if(PublicUtil.isEmpty(password)) return R.error("请输入密码");
		SysUserVO menus = permissionService.auth(userName, password);
		return R.ok(menus);
	}

}
