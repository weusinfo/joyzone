package com.joyzone.platform.core.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.JacksonUtil;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.RestTemplateUtil;
import com.joyzone.platform.config.EasemobConfig;
import com.joyzone.platform.core.dto.EasemobToken;
import com.joyzone.platform.core.dto.EasemobUser;
import com.joyzone.platform.core.dto.EasemobUserResponse;

import cn.hutool.json.JSONObject;


@Service
public class ChatService {

	@Autowired
	private EasemobConfig easemob;
	
	@Autowired
	private RedisService redisService;
	
	private Logger logger = LoggerFactory.getLogger(ChatService.class);
	
	/**
	 * Get easemob token
	 * @return
	 * @throws Exception
	 */
	public EasemobToken getToken(){
		try {
			Set<Object> tokens = redisService.sGet(Constants.EASEMOB_TOKEN);
			if(PublicUtil.isEmpty(tokens)) {			
				String accessUrl = easemob.getTokenUrl();
				Map<String, String> params = new HashMap<String, String>();
				params.put(Constants.EASEMOB_GRANT_TYPE, easemob.getGrantType());
				params.put(Constants.EASEMOB_CLIENT_ID, easemob.getClientId());
				params.put(Constants.EASEMOB_CLIENT_SECRET, easemob.getClientSecret());
				String body = JacksonUtil.deserializer(params);
				Map<String,String> headers = Maps.newHashMap();
				headers.put("content-type", MediaType.APPLICATION_JSON_VALUE);
				headers.put("Acccept", MediaType.APPLICATION_JSON_VALUE);
				String result = RestTemplateUtil.sendJson(accessUrl, body, headers, null);
				if(PublicUtil.isNotEmpty(result)) {
					EasemobToken token = JacksonUtil.parseJson(result, EasemobToken.class);
					redisService.sSetAndTime(Constants.EASEMOB_TOKEN, token.getExpiresIn() / 2,result);
					return token;
				}
				logger.error("Try to get token but failed， return null");
				return null;
			}else {
				Iterator<Object> ite = tokens.iterator();
				while(ite.hasNext()) {
					EasemobToken token = JacksonUtil.parseJson(String.valueOf(ite.next()), EasemobToken.class);
					return token;
				}
			}
		}catch(Exception e) {
			logger.error("Error happened when try to get token..。");
		}
		return null;
	}
	
	public Map<String,String> getAuthHeaders(){
		Map<String,String> headers = Maps.newHashMap();
		headers.put("content-type", MediaType.APPLICATION_JSON_VALUE);
		headers.put("Acccept", MediaType.APPLICATION_JSON_VALUE);
		headers.put(Constants.EASEMOB_HEADER_AUTH, "Bearer " + getToken().getToken());
		return headers;
	}
	
	/**
	 * Register easemob user
	 * @param userName
	 * @param password
	 * @return
	 */
	public EasemobUser registerUser(String userName, String password) {
		String userOpeUrl = easemob.getOpeUsersUrl();
		Map<String,String> headers = getAuthHeaders();
		Map<String,String> params = Maps.newHashMap();
		params.put("username", userName);
		params.put("password", password);
		String jsonStr = JacksonUtil.deserializer(params);
		try {
			String result = RestTemplateUtil.sendJson(userOpeUrl, jsonStr, headers, null);
			if(PublicUtil.isNotEmpty(result)) {
				EasemobUserResponse easemobUser = JacksonUtil.parseJson(result, EasemobUserResponse.class);
				return easemobUser.getEntities().get(0);
			}
		} catch (Exception e) {
			logger.error("Register easemob user and error happended...");
		}
		return null;
	}
	
	/**
	 * Change pwd
	 * @param userName
	 * @param newPwd
	 * @return
	 */
	public boolean changePwd(String userName, String newPwd) {
		String pwdUrl = easemob.getPwdUrl();
		pwdUrl = pwdUrl.replace("{userName}", userName);
		Map<String,String> headers = getAuthHeaders();
		Map<String,String> params = Maps.newHashMap();
		params.put("newpassword", newPwd);
		String jsonStr = JacksonUtil.deserializer(params);
		try {
			String result = RestTemplateUtil.sendJson(pwdUrl, jsonStr, headers, HttpMethod.PUT);
			if(PublicUtil.isNotEmpty(result)) {
				return true;
			}
		}catch(Exception e) {
			logger.error("Change PWD error happended...");
		}
		return false;
	}
	
	/**
	 * 创建聊天群
	 * @param ownerId
	 * @param groupName
	 */
	public String createGroup(Long ownerId, String groupName,String desc) {
		String createGroupUrl = easemob.getChatGrpUrl();
		Map<String,Object> map = Maps.newHashMap();
		map.put("groupname", groupName);
		map.put("desc", desc);
		map.put("public", true);
		map.put("maxusers", Constants.PARAM_CHATGROUP_MAXUSERS);
		map.put("approval", false);
		map.put("owner", ownerId+"");
		String jsonStr = JacksonUtil.deserializer(map);
		Map<String,String> headers = getAuthHeaders();
		try {
			String result = RestTemplateUtil.sendJson(createGroupUrl, jsonStr, headers, HttpMethod.POST);
			if(PublicUtil.isNotEmpty(result)) {
				logger.info(String.format("==== %s 创建群 %s 成功...", ownerId, groupName));
				JsonObject jsonObj = new JsonParser().parse(result).getAsJsonObject();
				JsonElement ele = jsonObj.get("data");
				JsonObject groupObj = ele.getAsJsonObject();
				return groupObj.get("groupid").getAsString();
			}
		} catch (Exception e) {
			logger.error(String.format("==== %s 创建群 %s 失败...", ownerId, groupName), e);
		}
		return null;
	}
	
	public void joinGroup(String groupId, Long userId) {
		String joinGroupUrl = easemob.getJoinGroupUrl();
		joinGroupUrl = joinGroupUrl.replace("{groupId}", groupId);
		joinGroupUrl = joinGroupUrl.replace("{userName}", userId+"");
		Map<String,String> headers = getAuthHeaders();
		Map<String,Object> map = Maps.newHashMap();
		try {
			String result = RestTemplateUtil.sendJson(joinGroupUrl, null, headers, HttpMethod.POST);
			if(PublicUtil.isEmpty(result)) {
				logger.error(String.format("用户 d% 加入群 d% 失败...", userId, groupId));
			}
		}catch(Exception e) {
			//
		}
	}
	
	public void cancelGroup(String groupId, Long userId) {
		String cancelGroupUrl = easemob.getRemoveMemberFromGroupUrl();
		cancelGroupUrl = cancelGroupUrl.replace("{groupId}",groupId);
		cancelGroupUrl = cancelGroupUrl.replace("{userName}",userId+"");
		Map<String,String> headers = getAuthHeaders();
		try {
			RestTemplateUtil.sendJson(cancelGroupUrl, null, headers, HttpMethod.DELETE);
		}catch(Exception e) {
			logger.error(String.format("从群组 d% 删除用户 userId d% 出错", groupId, userId), e);
		}
		
	}
}
