package com.joyzone.platform.module.admin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.SysUserModel;
import com.joyzone.platform.core.service.SysUserService;
import io.swagger.annotations.Api;

@Api(tags="1.0",value="系统（门店）用户管理",description="系统（门店）用户管理")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	public R addUser(SysUserModel sysUser) {
		sysUserService.addUser(sysUser);
		return R.ok("添加用户成功");
	}
	
	public R deleteUser() {
		return R.ok("删除用户成功");
	}
	
	public R updateUser() {
		return R.ok("更新用户成功");
	}
	
	public R listUsers(SysUserModel sysUser) {
		List<SysUserModel> users = sysUserService.listUsers(sysUser);
		if(PublicUtil.isEmpty(users)) return R.error("没有查询到用户");
		return R.ok().put("sysUsers", users);
	}

}
