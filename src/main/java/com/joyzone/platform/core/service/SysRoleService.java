package com.joyzone.platform.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.SysRoleMapper;
import com.joyzone.platform.core.model.SysRoleModel;

@Service
@Transactional
public class SysRoleService extends BaseService<SysRoleModel> {
	
	@Autowired
	private SysRoleMapper roleMapper;
	
	public void updateRole(SysRoleModel role) {
		if(PublicUtil.isEmpty(role.getRoleName())) throw new JZException("角色名不能为空");
		if(PublicUtil.isEmpty(role.getMenuIds())) throw new JZException("不能没有任何权限");
		if(role.getId() == null) {
			role.setCreateTime(new Date());
			role.setStatus(0);
			roleMapper.addRole(role);
			roleMapper.addMenus(role.getMenuIds(), role.getId());
		}else {
			roleMapper.updateRole(role);
			roleMapper.deleteMenusByRoleId(role.getId());
			roleMapper.addMenus(role.getMenuIds(), role.getId());
		}
	}
	
	public void grantRole(Long roleId, Long userId) {
		roleMapper.revokeRole(userId);
		roleMapper.grantRole(roleId, userId);
	}

	public List<SysRoleModel> getRoleList(SysRoleModel sysRoleModel){
		return roleMapper.getRoleList(sysRoleModel);
	}

	public List<SysRoleModel> getUserRoleList(Long userId){
		return roleMapper.getUserRoleList(userId);
	}
}
