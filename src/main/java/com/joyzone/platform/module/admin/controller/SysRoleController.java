package com.joyzone.platform.module.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.SysRoleModel;
import com.joyzone.platform.core.service.SysRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sysRole")
@Api(value="SysRoleController", tags="系统后台角色设置")
public class SysRoleController {
	
	@Autowired
	private SysRoleService roleService;
	
	@ApiOperation(value="添加商户文件", notes="添加角色和修改角色同一接口,有roleId,则认为是更新,没有则是新增")
	@ApiImplicitParam(name="status", value="权限状态;0:生效;1:失效.新增时默认为生效")
	@PostMapping("/update")
	public R updateRole(SysRoleModel role) {
		roleService.updateRole(role);
		return R.ok("权限设置成功");
	}

}
