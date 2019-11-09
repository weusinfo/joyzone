package com.joyzone.platform.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyzone.platform.core.mapper.InvitingMapper;

/**
 * 用于搜集群组
 * @author louishe
 *
 */
@Service
public class GroupTaskService {
	
	@Autowired
	private InvitingMapper invitingMapper;
	
	@Autowired
	private ChatService chatService;
	
	/**
	 * 清理过时和失效的邀请和群组
	 */
	public void cleanDisabledInvitingGroup() {
		invitingMapper.disableExpiredInviting();
		List<String> disabledInvitingGroupIds = invitingMapper.getDisabledInvitingGroupIds();
		if(!CollectionUtils.isEmpty(disabledInvitingGroupIds)) {
			for(String groupId : disabledInvitingGroupIds) {
				chatService.deleteTeamGroup(groupId);
			}
		}
		
	}

}
