package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.LabelModel;

import tk.mybatis.mapper.common.Mapper;

public interface LabelMapper extends Mapper<LabelModel>{
	
	public int addLabel(LabelModel labelModel);
	
	public int delLabel(String labelId, Long userId);
	
	public int updateLabel(LabelModel labelModel);
	
	public int disableModel(Long labelId, Long userId);

}