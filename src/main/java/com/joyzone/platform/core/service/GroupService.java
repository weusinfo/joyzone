package com.joyzone.platform.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.core.model.ShopModel;
import com.joyzone.platform.core.model.SysParamsModel;
import com.joyzone.platform.core.model.SysUserModel;
import com.joyzone.platform.core.model.TeamModel;

@Service
public class GroupService {
	
	private Logger LOGGER = LoggerFactory.getLogger(GroupService.class);
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private SysParamsService paramService;
	
	public String createChatGroup(Long shopId) {
		ShopModel shop = shopService.findById(shopId);
		if(PublicUtil.isEmpty(shop)) {
			LOGGER.warn(String.format("ShopId d% 没有找到对应的商家", shopId));
			return null;
		}
		Long owner = null;
		SysUserModel shopUser = sysUserService.selectByShopId(shopId);
		if(PublicUtil.isEmpty(shopUser)) {
			LOGGER.warn(String.format("本门店 d% 没有店小二", shopId));
			SysParamsModel param = paramService.findByName(Constants.PARAM_CHATGROUP_OWNER);
			if(PublicUtil.isEmpty(param) || PublicUtil.isEmpty(param.getParamValue())) return null;
			owner = Long.parseLong(param.getParamValue());
		}else {
			owner = shopUser.getId();
		}
		String shopName = shop.getName();
		return chatService.createGroup(owner, shopName, "组队建群");
	}
	
	public void joinChatGroup(Long teamId, Long userId) {
		TeamModel team = new TeamModel();
		team.setId(teamId);
		team = teamService.selectOne(team);
		chatService.joinGroup(team.getChatGroupId(), userId);
	}

}
