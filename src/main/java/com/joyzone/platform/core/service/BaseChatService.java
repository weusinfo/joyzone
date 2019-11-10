package com.joyzone.platform.core.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.JacksonUtil;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.RestTemplateUtil;
import com.joyzone.platform.config.EasemobConfig;
import com.joyzone.platform.core.dto.EasemobToken;
import com.joyzone.platform.core.dto.EasemobUser;
import com.joyzone.platform.core.dto.EasemobUserResponse;

/**
 * 
 * @author louishe
 *
 */
public class BaseChatService {
	
	@Autowired
	protected EasemobConfig easemob;
	
	@Autowired
	protected RedisService redisService;
	
	private Logger LOGGER = LoggerFactory.getLogger(ChatService.class);
	
	/**
	 * Register easemob user
	 * @param userName
	 * @param password
	 * @return
	 */
	@Async
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
			LOGGER.error("Register easemob user and error happended...",e);
		}
		return null;
	}
	
	/**
	 *Update user nick name
	 * @param userName
	 * @param nickName
	 */
	@Async
	public void updateUser(String userName, String nickName) {
		String userOpeUrl = easemob.getOpeUsersUrl() + "/" + userName;
		Map<String,String> headers = getAuthHeaders();
		Map<String,String> params = Maps.newHashMap();
		params.put("nickname", nickName);
		String jsonStr = JacksonUtil.deserializer(params);
		try {
			RestTemplateUtil.sendJson(userOpeUrl, jsonStr, headers, HttpMethod.PUT);
		}catch(Exception e) {
			LOGGER.error("Update easemob user and error happened...",e);
		}
	}
	
	/**
	 * Get Easemob token
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
				LOGGER.error("Try to get token but failed， return null");
				return null;
			}else {
				Iterator<Object> ite = tokens.iterator();
				while(ite.hasNext()) {
					EasemobToken token = JacksonUtil.parseJson(String.valueOf(ite.next()), EasemobToken.class);
					return token;
				}
			}
		}catch(Exception e) {
			LOGGER.error("Error happened when try to get token...",e);
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
			LOGGER.error("Change PWD error happended...");
		}
		return false;
	}

}
