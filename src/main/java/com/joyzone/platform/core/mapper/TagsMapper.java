package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.TagsModel;

import tk.mybatis.mapper.common.Mapper;

public interface TagsMapper extends Mapper<TagsModel>{
	
	public void addTag(TagsModel tagModel);
	
	public int delTag(String tagId, Long userId);
	
	public void updateTag(TagsModel tagModel);
	
	public void disableTag(Long tagId, Long userId);

}
