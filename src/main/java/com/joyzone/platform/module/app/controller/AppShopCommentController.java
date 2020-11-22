package com.joyzone.platform.module.app.controller;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.ShopCommentDTO;
import com.joyzone.platform.core.service.ShopCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app_shop_comment")
@Api(tags = "app商家评论相关接口",description = "AppShopCommentController")
public class AppShopCommentController {

    @Autowired
    private ShopCommentService shopCommentService;


    @PostMapping("/list")
    @ApiOperation("商家评论列表")
    public R listShopComment(Long shopId, Integer pageNum, Integer pageSize){
        return this.shopCommentService.listShopComments(shopId,pageNum,pageSize);
    }


    @PostMapping("/create")
    @ApiOperation("商家评论")
    public R createShopComment(ShopCommentDTO shopCommentDTO){
        return this.shopCommentService.create(shopCommentDTO);
    }

}
