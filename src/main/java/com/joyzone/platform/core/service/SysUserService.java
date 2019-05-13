package com.joyzone.platform.core.service;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.SysUserMapper;
import com.joyzone.platform.core.model.SysUserModel;
import cn.hutool.crypto.digest.BCrypt;

@Service
@Transactional
public class SysUserService extends BaseService<SysUserModel> {

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private ShopService shopService;
	
	public void addUser(SysUserModel sysUser) {
		if(StringUtils.isEmpty(sysUser.getName())) throw new JZException("用户名不能为空");
		if(StringUtils.isEmpty(sysUser.getPassword())) throw new JZException("密码不能为空");
		if(StringUtils.isEmpty(sysUser.getPhone())) throw new JZException("手机号码不能为空");
		if(PublicUtil.isEmpty(sysUser.getSex())) throw new JZException("性别不能为空");
		if(PublicUtil.isEmpty(sysUser.getShopId())) throw new JZException("用户应该与店家关联");
		if(!shopService.exists(sysUser.getShopId())) throw new JZException("该商户状态异常");
		sysUser.setPassword(BCrypt.hashpw(sysUser.getPassword()));
		sysUser.setCreateTime(new Date());
		sysUser.setStatus(0);
		sysUser.setTryErrTimes(0);
		sysUserMapper.insert(sysUser);
	}
	
	public void updateUser(SysUserModel sysUser) {
		if(StringUtils.isEmpty(sysUser.getName())) throw new JZException("用户名不能为空");
		if(StringUtils.isEmpty(sysUser.getPassword())) throw new JZException("密码不能为空");
		if(StringUtils.isEmpty(sysUser.getPhone())) throw new JZException("手机号码不能为空");
		if(PublicUtil.isEmpty(sysUser.getSex())) throw new JZException("性别不能为空");
		if(PublicUtil.isEmpty(sysUser.getShopId())) throw new JZException("用户应该与店家关联");
		if(!shopService.exists(sysUser.getShopId())) throw new JZException("该商户状态异常");
		sysUser.setPassword(BCrypt.hashpw(sysUser.getPassword()));
		sysUser.setUpdateTime(new Date());
		sysUserMapper.updateSysUser(sysUser);
	}
	
	public List<SysUserModel> listUsers(SysUserModel sysUser){
		List<SysUserModel> sysUsers = sysUserMapper.listUsers(sysUser);
		if(PublicUtil.isEmpty(sysUsers)) throw new JZException("没有找到用户");
		return sysUsers;
	}
	
	public void deleteUsers(Long[] ids) {
		sysUserMapper.batchDelete(ids);
	}
	
	public boolean checkUserByName(String userName) {
		return sysUserMapper.checkUserByName(userName).equals(1) ? true : false;
	}
	
	public Integer logLogin(String userName) {
		return sysUserMapper.logErrLogin(userName);
	}
	
	public Integer checkErrLoginCount(@Param("userName") String userName) {
		return sysUserMapper.checkErrLoginCount(userName);
	}
	
	public SysUserModel selectByName(@Param("userName") String userName) {
		return sysUserMapper.selectByName(userName);
	}

	/**
	 * 数据中不包含密码属性
	 * @param id
	 * @return
	 */
	public SysUserModel selectById(Long id){
		return sysUserMapper.selectById(id);
	}
}
