package com.joyzone.platform.module.admin.controller;

import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.ForumModel;
import com.joyzone.platform.core.service.ForumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/forum")
@Api(value="后台论坛管理",tags="1.0")
public class ForumController {

    @Autowired
    private ForumService forumService;

    /**
     * Mr.Gx
     */
    @GetMapping("getForumList")
    @ApiOperation("获取论坛清单")
    public R getForumList(ForumModel forumModel){
       List<ForumModel> list = forumService.getForumList(forumModel);
       if(list != null && list.size() > 0){
           Page page = new Page();
           page = (Page)list;
           return R.pageToData(page.getTotal(),page.getResult());
       }
       return  R.pageToData(0L,new ArrayList<>());
    }
}
