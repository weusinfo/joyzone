package com.joyzone.platform.module.admin.controller;

import java.util.List;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.SysUserModel;
import com.joyzone.platform.core.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sysUser")
@Api(tags="后台（门店）用户管理",description="SysUserController")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@ApiOperation("更新商户用户")
	@PostMapping("/add")
	public R addUser(SysUserModel sysUser) {
		sysUserService.addUser(sysUser);
		return R.ok("添加用户成功");
	}
	
	@ApiOperation("删除商户商户")
	@PostMapping("/delete")
	public R deleteUser(Long[] ids) {
		sysUserService.deleteUsers(ids);
		return R.ok("删除用户成功");
	}
	
	@ApiOperation("更新商户用户信息")
	@PostMapping("/update")
	public R updateUser(SysUserModel sysUser) {
		sysUserService.updateUser(sysUser);
		return R.ok("更新用户成功");
	}
	
	@ApiOperation("获取商户用户列表")
	@PostMapping("/list")
	public R listUsers(SysUserModel sysUser) {
		List<SysUserModel> users = sysUserService.listUsers(sysUser);
		if(users != null && users.size() > 0){
			Page page = new Page();
			page = (Page)users;
			return R.pageToData(page.getTotal(),page.getResult());
		}
		return R.pageToData(0L,users);
	}

}
