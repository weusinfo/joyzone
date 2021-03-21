package com.joyzone.platform.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.TagsMapper;
import com.joyzone.platform.core.model.TagsModel;

@Service
@Transactional
public class TagsService extends BaseService<TagsModel>{
	
	@Autowired
	private TagsMapper tagsMapper;
	
	/**
	 * 添加用户兴趣标签
	 * @param tagsModel
	 */
	public void addTag(TagsModel tagsModel) {
		
	}
	
	/**
	 * 更新用户标签
	 * @param tagsModel
	 */
	public void updateTag(TagsModel tagsModel) {
		
	}
	
	/**
	 * 删除标签
	 * @param tagId
	 */
	public void delTag(Long tagId, Long userId) {
		
	}
	
	/**
	 * disable 标签
	 * @param tagId
	 */
	public void disableTag(Long tagId, Long userId) {
		
	}

}
