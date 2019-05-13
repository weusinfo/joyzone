package com.joyzone.platform.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyzone.platform.core.base.BaseMapper;
import com.joyzone.platform.core.model.SysUserModel;

public interface SysUserMapper extends BaseMapper<SysUserModel> {

	int updateSysUser(SysUserModel sysUser);
	
	int batchDelete(@Param("ids") Long[] ids);
	
	Integer checkUserByName(@Param("userName") String userName);
	
	Integer logErrLogin(@Param("userName") String userName);
	
	Integer checkErrLoginCount(@Param("userName") String userName);
	
	List<SysUserModel> listUsers(SysUserModel sysUser);
	
	SysUserModel selectByName(@Param("userName") String userName);

	SysUserModel selectById(Long id);
}
