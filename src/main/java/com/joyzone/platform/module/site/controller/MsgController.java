package com.joyzone.platform.module.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.SiteMsgDto;
import com.joyzone.platform.core.service.CompanyMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/company")
@Api(tags="官站李艳相关", description = "CompanyMessageController")
public class MsgController {
	
	@Autowired
	private CompanyMessageService msgService;
	
	/**
	 * 公司网站留言
	 * @param msgDto
	 * @return
	 */
	@PostMapping("/postMsg")
	 @ApiOperation("官站留言接口")
	public R postMsg(SiteMsgDto msgDto) {
		return msgService.save(msgDto);
	}

}
