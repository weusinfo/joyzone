package com.joyzone.platform.core.mapper;

import org.apache.ibatis.annotations.Param;

import com.joyzone.platform.core.model.SysParamsModel;
import tk.mybatis.mapper.common.Mapper;

public interface SysParamsMapper extends Mapper<SysParamsModel> {
	
	SysParamsModel findByName(@Param("paramName") String paramName);
}