package com.joyzone.platform.core.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.SiteMsgDto;
import com.joyzone.platform.core.mapper.CompanyMessageMapper;
import com.joyzone.platform.core.model.CompanyMessage;

@Service
public class CompanyMessageService extends BaseService<CompanyMessage>{

	@Autowired
	private CompanyMessageMapper msgMapper;
	
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
		return R.ok("留言成功，我们会尽快给您回复");
	}
}
