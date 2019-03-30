package com.joyzone.platform.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.SysUserMapper;
import com.joyzone.platform.core.model.SysUserModel;

@Service
public class SysUserService extends BaseService<SysUserModel> {

	@Autowired
	private SysUserMapper sysUserMapper;
	
	public void addUser(SysUserModel sysUser) {
		if(StringUtils.isEmpty(sysUser.getName())) throw new JZException("用户名不能为空");
		if(StringUtils.isEmpty(sysUser.getPassword())) throw new JZException("密码不能为空");
		if(StringUtils.isEmpty(sysUser.getPhone())) throw new JZException("手机号码不能为空");
		if(PublicUtil.isEmpty(sysUser.getSex())) throw new JZException("性别不能为空");
		sysUserMapper.insert(sysUser);
	}
	
	public void updateUser(SysUserModel sysUser) {
		sysUserMapper.updateSysUser(sysUser);
	}
	
	public List<SysUserModel> listUsers(SysUserModel sysUser){
		return select(sysUser);
	}
}
