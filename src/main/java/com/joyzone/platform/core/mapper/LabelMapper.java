package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.LabelModel;

import tk.mybatis.mapper.common.Mapper;

public interface LabelMapper extends Mapper<LabelModel>{
	
	public void addLabel(LabelModel labelModel);
	
	public int delLabel(String labelId, Long userId);
	
	public void updateLabel(LabelModel labelModel);
	
	public void disableModel(Long labelId, Long userId);

}