package com.joyzone.platform.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.core.service.TeamService;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * 组队超时处理。
 * @author louishe
 *
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener{
	
	@Autowired
	private TeamService teamService;
	
	public RedisKeyExpirationListener(RedisMessageListenerContainer container) {
		super(container);
	}
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		String expiredKey = message.toString();
		if(StringUtils.isNotEmpty(expiredKey) && expiredKey.startsWith(Constants.KEY_EXPIRATION_PREFIX)) {
			String invitingId = expiredKey.substring(7);
			teamService.failInviting(invitingId);
		}
	}

}
