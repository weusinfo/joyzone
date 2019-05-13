package com.joyzone.platform.module.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.SysRoleModel;
import com.joyzone.platform.core.service.SysRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping("/sysRole")
@Api(value="SysRoleController", tags="后台角色设置")
public class SysRoleController {
	
	@Autowired
	private SysRoleService roleService;
	
	@ApiOperation(value="系统角色设置", notes="添加角色和修改角色同一接口,有roleId,则认为是更新,没有则是新增")
	@ApiImplicitParam(name="status", value="权限状态;0:生效;1:失效.新增时默认为生效", paramType="query")
	@PostMapping("/update")
	public R updateRole(SysRoleModel role) {
		roleService.updateRole(role);
		return R.ok("权限设置成功");
	}

	@ApiOperation(value="为用户设定角色", notes="给某各用户设定角色, 使之有某菜单权限")
	@PostMapping("/grant")
	public R grantRole(@RequestParam("roleId") Long roleId, @RequestParam("userId") Long userId) {
		if(roleId == null || userId == null) return R.error("缺少必要参数");
		roleService.grantRole(roleId, userId);
		return R.ok("角色设置成功");
	}

	@ApiOperation(value="获取角色列表")
	@PostMapping("/getRoleList  @Mr.Gx")
	public R getRoleList() {
		return R.ok(roleService.getRoleList());
	}
}
