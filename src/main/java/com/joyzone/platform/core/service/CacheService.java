package com.joyzone.platform.core.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyzone.platform.common.utils.JacksonUtil;
import com.joyzone.platform.core.model.UserModel;

/**
 * 缓存信息处理类
 * @author louishe
 *
 */
@Component
public class CacheService {
	
	public final static String KEY_USER = "USER";
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 把用户信息压入缓存
	 * @param user
	 */
	public void apdUser(UserModel user) {
		String strUser = JacksonUtil.deserializer(user);
		redisService.hset(KEY_USER, user.getId().toString(), strUser);
	}
	
	/**
	 * 删除一个或多个用户
	 * @param ids
	 */
	public void delUser(Long... ids) {
		if(ids != null && ids.length > 0) {
			redisService.hdel(KEY_USER, ids);
		}
	}
	
	/**
	 * 删除所有用户信息
	 */
	public void deleteAllUsers() {
		redisService.del(KEY_USER);
	}
	
	/**
	 * 获取缓存中的用户信息
	 * @param id
	 * @return
	 */
	public UserModel getUserById(String id) {
		String strUser = (String)redisService.hget(KEY_USER, id);
		UserModel user = null;
		if(strUser != null) {
			try {
				user = JacksonUtil.parseJson(strUser, UserModel.class);
			} catch (IOException e) {
				user = null;
			}
		}
		return user;
	}

}
