package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.base.BaseMapper;
import com.joyzone.platform.core.model.SysUserModel;

public interface SysUserMapper extends BaseMapper<SysUserModel> {

	int updateSysUser(SysUserModel sysUser);
}
