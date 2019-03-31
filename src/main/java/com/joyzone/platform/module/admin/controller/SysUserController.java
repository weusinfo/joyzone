package com.joyzone.platform.module.admin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.SysUserModel;
import com.joyzone.platform.core.service.SysUserService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/sysUser")
@Api(tags="1.0",value="系统（门店）用户管理",description="系统（门店）用户管理")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@PostMapping("/add")
	public R addUser(SysUserModel sysUser) {
		sysUserService.addUser(sysUser);
		return R.ok("添加用户成功");
	}
	
	@PostMapping("/delete")
	public R deleteUser(Long[] ids) {
		sysUserService.deleteUsers(ids);
		return R.ok("删除用户成功");
	}
	
	@PostMapping("/update")
	public R updateUser() {
		return R.ok("更新用户成功");
	}
	
	@PostMapping("/list")
	public R listUsers(SysUserModel sysUser) {
		List<SysUserModel> users = sysUserService.listUsers(sysUser);
		return R.ok(users);
	}

}
