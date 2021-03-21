package com.joyzone.platform.module.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.service.TagsService;

import io.swagger.annotations.Api;

/**
 * 用户兴趣标签
 * @author louishe
 *
 */
@RestController
@RequestMapping("/")
@Api(description = "TagsController",tags = "个人兴趣标签")
public class TagsController {
	
	@Autowired
	private TagsService tagService;
	
	/*
	 * 用于用户登录首页获取用户兴趣标签
	 */
	public R getTags(){
	
		return R.ok("");
	}

}
