package com.joyzone.platform.core.mapper;

import java.util.List;

import com.joyzone.platform.core.model.TagsModel;

import tk.mybatis.mapper.common.Mapper;

public interface TagsMapper extends Mapper<TagsModel>{
	
	public int addTag(TagsModel tagModel);
	
	public int delTag(Long tagId, Long userId);
	
	public int updateTag(TagsModel tagModel);
	
	public int disableTag(Long tagId, Long userId);
	
	public List<TagsModel> getTags();

}
