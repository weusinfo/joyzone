package com.joyzone.platform.core.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
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
	
	@Autowired
	private ShopCouponService couponService;
	
	public Map<String, Object> getService(Long shopId) {
		Map<String,Object> service = Maps.newHashMap();
		ShopModel shop = shopService.findById(shopId);
		if(PublicUtil.isEmpty(shop)) {
			LOGGER.warn(String.format("ShopId d% 没有找到对应的商家", shopId));
			return null;
		}
		Long owner = null;
		SysUserModel shopUser = sysUserService.selectByShopId(shopId);
		if(PublicUtil.isEmpty(shopUser)) {
			LOGGER.warn(String.format("本门店 %d 没有店小二", shopId));
			SysParamsModel param = paramService.findByName(Constants.PARAM_CHATGROUP_OWNER);
			if(PublicUtil.isEmpty(param) || PublicUtil.isEmpty(param.getParamValue())) return null;
			owner = Long.parseLong(param.getParamValue());
		}else {
			owner = shopUser.getId();
		}
		service.put("owner", owner);
		service.put("shopName", shop.getName());
		return service;
	}
	
	public String createTeamGroup(Long shopId) {
		Map<String, Object> map = getService(shopId);
		return chatService.createTeamGroup((Long)map.get("owner"), (String)map.get("shopName"), "组队建群");
	}
	
	public void joinCouponGroup(Long couponId, Long userId) {
		Map<String, Object> couponMap = couponService.getCouponInfo(couponId);
		if(PublicUtil.isEmpty(couponMap.get("chatGroupId"))) {
			Map<String, Object> map = getService((Long) couponMap.get("shopId"));
			String groupId = chatService.createTeamGroup((Long)map.get("owner"), (String)couponMap.get("name"),  (String)map.get("shopName") + "--" + (String)couponMap.get("name"));
			couponService.updChatGroupId(groupId, couponId);
			chatService.joinTeamGroup(groupId, userId);
		}else {
			chatService.joinTeamGroup((String)couponMap.get("chatGroupId"), userId);
		}
	}
	
	public void joinChatGroup(Long teamId, Long userId) {
		TeamModel team = new TeamModel();
		team.setId(teamId);
		team = teamService.selectOne(team);
		chatService.joinTeamGroup(team.getChatGroupId(), userId);
	}
	
	public void cancelGroup(Long teamId, Long userId) {
		TeamModel teamModel = new TeamModel();
		teamModel.setId(teamId);
		teamModel = teamService.selectOne(teamModel);
		String groupId = teamModel.getChatGroupId();
		if(PublicUtil.isEmpty(groupId)){
			LOGGER.warn(String.format("Team ID %d 没有环信群ID", teamId));
			return;
		}
		chatService.cancelTeamGroup(groupId, userId);
	}
	
	public void deleteGroup(Long teamId) {
		TeamModel team = new TeamModel();
		team.setId(teamId);
		team = teamService.selectOne(team);
		String groupId = team.getChatGroupId();
		if(PublicUtil.isEmpty(groupId)) {
			LOGGER.error(String.format("TeamID %d 没有群ID", teamId));
		}
		chatService.deleteTeamGroup(String.valueOf(teamId));
	}

}
