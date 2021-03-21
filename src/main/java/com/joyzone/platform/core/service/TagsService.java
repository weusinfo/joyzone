package com.joyzone.platform.core.service;

import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Maps;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.RedisUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.TagsMapper;
import com.joyzone.platform.core.model.TagsModel;

import cn.hutool.core.collection.CollectionUtil;

@Service
@Transactional
public class TagsService extends BaseService<TagsModel>{
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private TagsMapper tagsMapper;
	
	/**
	 * 添加用户兴趣标签
	 * @param tagsModel
	 */
	public void addTag(TagsModel tagsModel) {
		if(StringUtil.isEmpty(tagsModel.getType())) {
			tagsModel.setType(Constants.INTEREST_TAGS_DEFAULT);
		}
		int i = tagsMapper.addTag(tagsModel);
		if(i > 0) {
			redisUtil.hset(Constants.CACHE_INTEREST_TAGS, tagsModel.getId() + "::" + tagsModel.getType(), tagsModel);
		}
	}
	
	/**
	 * 更新用户标签
	 * @param tagsModel
	 */
	public void updateTag(TagsModel tagsModel) {
		int i = tagsMapper.updateTag(tagsModel);
		if(i > 0) {
			redisUtil.hdel(Constants.CACHE_INTEREST_TAGS, tagsModel.getId()+"::"+tagsModel.getType());
			TagsModel model = selectByKey(tagsModel.getId());
			redisUtil.hset(Constants.CACHE_INTEREST_TAGS, tagsModel.getId()+"::"+tagsModel.getType(),model);
		}
	}
	
	/**
	 * 删除标签
	 * @param tagId
	 */
	public void delTag(Long tagId, Long userId) {
		TagsModel model = selectByKey(tagId);
		int i = tagsMapper.delTag(tagId, userId);
		if(i > 0) {
			redisUtil.hdel(Constants.CACHE_INTEREST_TAGS, model.getId()+"::"+model.getType());
		}
	}
	
	/**
	 * disable 标签
	 * @param tagId
	 */
	public void disableTag(Long tagId, Long userId, String type) {
		int i = tagsMapper.disableTag(tagId, userId);
		if(i > 0) {
			redisUtil.hdel(Constants.CACHE_INTEREST_TAGS,tagId + "::"+type);
		}
	}
	
	/**
	 * 获取系统推荐标签
	 * @return
	 */
	public Map<String,List<TagsModel>> getTags(){
		redisUtil.get(Constants.CACHE_INTEREST_TAGS);
		List<TagsModel> tags = tagsMapper.getTags();
		Map<String,List<TagsModel>> map = Maps.newHashMap();
		if(CollectionUtil.isNotEmpty(tags)) {
			for(TagsModel tag: tags) {
				if(map.get(tag.getName()) != null) {
					List<TagsModel> models = Lists.newArrayList();
					models.add(tag);
					map.put(tag.getName(), models);
				}else {
					map.get(tag.getName()).add(tag);
				}
			}
		}
		return map;
	}

}
