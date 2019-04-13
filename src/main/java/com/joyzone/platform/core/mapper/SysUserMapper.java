package com.joyzone.platform.core.mapper;

import org.apache.ibatis.annotations.Param;

import com.joyzone.platform.core.base.BaseMapper;
import com.joyzone.platform.core.model.SysUserModel;

public interface SysUserMapper extends BaseMapper<SysUserModel> {

	int updateSysUser(SysUserModel sysUser);
	
	int batchDelete(Long[] ids);
	
	Integer checkUserByName(@Param("userName") String userName);
	
	SysUserModel selectUserByPwd(@Param("userName") String userName, @Param("password") String password);
}
