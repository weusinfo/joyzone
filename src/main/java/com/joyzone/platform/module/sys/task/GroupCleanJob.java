package com.joyzone.platform.module.sys.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyzone.platform.core.service.GroupTaskService;

/**
 * 定时清理完成组队和过期的群组
 * @author louishe
 *
 */
@Component
public class GroupCleanJob implements Job{

	@Autowired
	private GroupTaskService groupTaskService;
	
	/**
	 * 用于任务执行无效的个人邀请和失效的群组
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		groupTaskService.cleanDisabledInvitingGroup();
	}

}
