package com.joyzone.platform.core.service;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.JacksonUtil;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.RestTemplateUtil;


@Service
public class ChatService extends BaseChatService{

	private Logger LOGGER = LoggerFactory.getLogger(ChatService.class);
	
	
	
	/**
	 * 创建聊天群
	 * @param ownerId
	 * @param groupName
	 */
	public String createTeamGroup(Long ownerId, String groupName,String desc) {
		String createGroupUrl = easemob.getChatGrpUrl();
		Map<String,Object> map = Maps.newHashMap();
		map.put("groupname", groupName);
		map.put("desc", desc);
		map.put("public", true);
		map.put("maxusers", Constants.PARAM_GROUP_MAXUSERS);
		map.put("approval", false);
		map.put("owner", ownerId+"");
		String jsonStr = JacksonUtil.deserializer(map);
		Map<String,String> headers = getAuthHeaders();
		try {
			String result = RestTemplateUtil.sendJson(createGroupUrl, jsonStr, headers, HttpMethod.POST);
			if(PublicUtil.isNotEmpty(result)) {
				LOGGER.info(String.format("==== %s 创建群 %s 成功...", ownerId, groupName));
				JsonObject jsonObj = new JsonParser().parse(result).getAsJsonObject();
				JsonElement ele = jsonObj.get("data");
				JsonObject groupObj = ele.getAsJsonObject();
				return groupObj.get("groupid").getAsString();
			}
		} catch (Exception e) {
			LOGGER.error(String.format("==== %s 创建群 %s 失败...", ownerId, groupName), e);
		}
		return null;
	}
	
	public void joinTeamGroup(String groupId, Long userId) {
		String joinGroupUrl = easemob.getJoinGroupUrl();
		joinGroupUrl = joinGroupUrl.replace("{groupId}", groupId);
		joinGroupUrl = joinGroupUrl.replace("{userName}", userId+"");
		Map<String,String> headers = getAuthHeaders();
		try {
			String result = RestTemplateUtil.sendJson(joinGroupUrl, null, headers, HttpMethod.POST);
			if(PublicUtil.isEmpty(result)) {
				LOGGER.error(String.format("用户 %d 加入群 %d 失败...", userId, groupId));
			}
		}catch(Exception e) {
			//
		}
	}
	
	public void cancelTeamGroup(String groupId, Long userId) {
		String cancelGroupUrl = easemob.getRemoveMemberFromGroupUrl();
		cancelGroupUrl = cancelGroupUrl.replace("{groupId}",groupId);
		cancelGroupUrl = cancelGroupUrl.replace("{userName}",userId+"");
		Map<String,String> headers = getAuthHeaders();
		try {
			RestTemplateUtil.sendJson(cancelGroupUrl, null, headers, HttpMethod.DELETE);
		}catch(Exception e) {
			LOGGER.error(String.format("从群组 d% 删除用户 userId %d 出错", groupId, userId), e);
		}
	}
	
	public void deleteTeamGroup(String groupId) {
		String delGroupUrl = easemob.getDeleteGroupUrl();
		delGroupUrl = delGroupUrl.replace("{groupId}", groupId);
		Map<String,String> headers = getAuthHeaders();
		try {
			RestTemplateUtil.sendJson(delGroupUrl, null, headers, HttpMethod.DELETE);
		}catch(Exception e) {
			LOGGER.error(String.format("删除群组 %s 出错", groupId), e);
		}
	}
}