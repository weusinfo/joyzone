package com.joyzone.platform.core.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.common.utils.SMSUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.SiteMsgDto;
import com.joyzone.platform.core.model.CompanyMessage;
import com.joyzone.platform.core.model.SysParamsModel;

@Service
public class CompanyMessageService extends BaseService<CompanyMessage>{
	
	private Logger LOGGER = LoggerFactory.getLogger(CompanyMessageService.class);
	
	private SMSUtil smsUtil;
	
	@Autowired
	private SysParamsService paramService;
	
	public R save(SiteMsgDto msgDto) {
		if(msgDto == null) {
			return R.error("请填写必要参数");
		}
		if(StringUtils.isEmpty(msgDto.getName())) {
			return R.error("昵称不能为空");
		}
		if(StringUtils.isEmpty(msgDto.getPhone())) {
			return R.error("电话号码不能为空");
		}
		CompanyMessage msg = new CompanyMessage();
		BeanUtils.copyProperties(msgDto, msg);
		save(msg);
		sendMsg(msgDto);
		return R.ok("留言成功，我们会尽快给您回复");
	}
	
	/**
	 * 发送短信通知某人在官网留言了
	 */
	@Async
	public void sendMsg(SiteMsgDto msgDto) {
		SysParamsModel param = paramService.findByName("WEBSITE_PHONE");
		if(param != null) {
			try {
				smsUtil.sendMsgToAdmin(param.getParamValue(), msgDto.getName(), msgDto.getPhone());
			} catch (ClientException e) {
				LOGGER.error("Website msg send code failed.",e);
			}
		}
		
	}
}
