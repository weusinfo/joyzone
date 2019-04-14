package com.joyzone.platform.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.SysParamsMapper;
import com.joyzone.platform.core.model.SysParamsModel;

@Service
public class SysParamsService extends BaseService<SysParamsModel>{
	
	@Autowired
	private SysParamsMapper paramsMapper;
	
	public SysParamsModel findByName(String paramName) {
		return paramsMapper.findByName(paramName);
	}

}
