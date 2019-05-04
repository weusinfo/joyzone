package com.joyzone.platform.module.app.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.TeamDto;
import com.joyzone.platform.core.model.ShopCollectModel;
import com.joyzone.platform.core.model.ShopModel;
import com.joyzone.platform.core.model.TeamModel;
import com.joyzone.platform.core.model.TeamUsersModel;
import com.joyzone.platform.core.service.ShopCollectService;
import com.joyzone.platform.core.service.TeamService;
import com.joyzone.platform.core.service.TeamUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/app_mine")
@Api(tags = "app我的模块相关接口",description = "AppMineApiController")
public class AppMineApiController {

    @Autowired
    private ShopCollectService shopCollectService;


    @PostMapping("/getMyShopCollectList")
    @ApiOperation("前端获取我的店家收藏列表 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query")
    })
    public R getMyShopCollectList(ShopCollectModel model,Long userId){
        PageHelper.startPage(0,10);
        List<ShopModel> shopList = shopCollectService.getMyShopCollectList(model,userId);
        if(shopList != null && shopList.size() > 0){
            Page page = new Page();
            page = (Page)shopList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }


}
