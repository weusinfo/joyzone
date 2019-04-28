package com.joyzone.platform.module.app.controller;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.ForumDetailModel;
import com.joyzone.platform.core.model.ForumModel;
import com.joyzone.platform.core.service.ForumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app_forum")
@Api(tags="app论坛相关操作", description = "AppForumController")
public class AppForumController {

    @Autowired
    private ForumService forumService;

    /**
     * Mr.Gx
     */
    @PostMapping("saveForum")
    @ApiOperation("添加及更新论坛信息  @Mr.Gx")
    public R saveForum(ForumModel forumModel){
        return forumService.saveForumNum(forumModel);
    }

    /**
     * Mr.Gx
     */
    @PostMapping("saveForumDetail")
    @ApiOperation("添加跟帖信息 @Mr.Gx")
    public R saveForumDetail(ForumDetailModel forumDetailModel){
        return forumService.saveForumDetail(forumDetailModel);
    }
}
