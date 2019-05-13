package com.joyzone.platform.core.service;

import java.util.List;

import com.joyzone.platform.core.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.core.dto.Menus;
import com.joyzone.platform.core.model.MenuModel;
import com.joyzone.platform.core.model.SysParamsModel;
import com.joyzone.platform.core.model.SysUserModel;
import cn.hutool.crypto.digest.BCrypt;

@Service
public class PermissionService {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysParamsService paramsService;
	
	@Autowired
	private MenuService menuService;
	
	public SysUserVO auth(String userName, String password) {
		if(!sysUserService.checkUserByName(userName)) throw new JZException("不存在该用户");
		SysParamsModel paramsModel = paramsService.findByName(Constants.PARAM_LOGIN_FORBIDDEN_NUM);
		if(paramsModel != null) {
			Integer errCount = sysUserService.checkErrLoginCount(userName);
			if(errCount >= Integer.valueOf(paramsModel.getParamValue())) {
				throw new JZException("您已超过试错次数， 请联系客服");
			}
		}
		SysUserModel sysUser = sysUserService.selectByName(userName);
		if(sysUser == null) {
			//log login fail time and retry count
			sysUserService.logLogin(userName);
			throw new JZException("密码错误");
		}
		boolean isMatched = BCrypt.checkpw(password, sysUser.getPassword());
		if(!isMatched) {
			sysUserService.logLogin(userName);
			throw new JZException("密码错误");
		}
		Long id = sysUser.getId();
		SysUserVO sysUserVO = new SysUserVO();
		sysUserVO.setUser(sysUserService.selectById(id));
		sysUserVO.setMenuList(menuService.list(id));
		return sysUserVO;
	}
}
