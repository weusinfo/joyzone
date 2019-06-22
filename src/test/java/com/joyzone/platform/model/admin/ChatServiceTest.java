package com.joyzone.platform.model.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joyzone.platform.PlayApplication;
import com.joyzone.platform.core.service.ChatService;
import com.joyzone.platform.core.service.GroupService;


@SpringBootTest(classes = PlayApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ChatServiceTest {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private GroupService groupService;
	
//	@Test
//	public void testCreateGroup() {
//		chatService.createTeamGroup(1111l, "test12", "Test create group.");
//	}
//	
//	@Test
//	public void testJoinGroup() {
//		chatService.joinTeamGroup("84791033659394", 13l);
//	}
//	
//	@Test
//	public void cancelGroup() {
//		chatService.cancelTeamGroup("84791033659394", 13l);
//	}
	
	@Test
	public void joinCouponGroup() {
		groupService.joinCouponGroup(4l, 20l);
	}

}
