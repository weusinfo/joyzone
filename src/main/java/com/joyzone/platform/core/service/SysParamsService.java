package com.joyzone.platform.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.JacksonUtil;
import com.joyzone.platform.common.utils.RedisUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.SysParamsMapper;
import com.joyzone.platform.core.model.SysParamsModel;
import com.mysql.jdbc.StringUtils;

@Service
public class SysParamsService extends BaseService<SysParamsModel>{
	
	@Autowired
	RedisUtil redisUtil;
	
	@Autowired
	private SysParamsMapper paramsMapper;
	
	public SysParamsModel findByName(String paramName) {
		Object obj = redisUtil.hget(Constants.CACHE_KEY, paramName);
		if(obj != null) {
			return JacksonUtil.serializable((String)obj, SysParamsModel.class);
		}
		
		SysParamsModel model = paramsMapper.findByName(paramName);
		if(model != null) {
			redisUtil.hset(Constants.CACHE_KEY, paramName, JacksonUtil.deserializer(model));
			return model;
		}
		return null;
	}
	
	public Long getTribeOwner() {
		SysParamsModel model = findByName(Constants.TRIBE_COMMITTEE);
		if(model != null) {
			String paramValue = model.getParamValue();
			if(StringUtils.isNullOrEmpty(paramValue)) {
				String[] committes = paramValue.split("|");
				return Long.parseLong(committes[0]);
			}
		}
		return null;
	}
	
	public List<String> getTribberGuests(){
		SysParamsModel model = findByName(Constants.TRIBE_COMMITTEE);
		if(model != null) {
			String paramValue = model.getParamValue();
			if(StringUtils.isNullOrEmpty(paramValue)) {
				String[] committes = paramValue.split("|");
				Splitter.on(",").splitToList(committes[1]);
			}
		}
		return null;
	}

}
