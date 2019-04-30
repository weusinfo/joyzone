package com.joyzone.platform.module.app.controller;


import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.TeamDto;
import com.joyzone.platform.core.model.ShopCouponModel;
import com.joyzone.platform.core.model.TeamModel;
import com.joyzone.platform.core.service.ShopCouponService;
import com.joyzone.platform.core.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app_team")
@Api(tags = "app首页店家组队列表相关接口",description = "AppTeamController")
public class AppTeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private ShopCouponService shopCouponService;

    /**
     * zy
     */
    @PostMapping("/getTeamList")
    @ApiOperation("前端获取体验券列表 @Zy")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sort", value = "0:热点 1：最新", required = true, dataType = "Integer", paramType = "query")
    })
    public R getTeamList(TeamModel teamModel, Integer sort){
        List<TeamDto> teamList = teamService.getTeamList(teamModel);
        if(teamList != null && teamList.size() > 0){
            Page page = new Page();
            page = (Page)teamList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    /**
     * zy
     */
    @PostMapping("/getCouponShopList")
    @ApiOperation("前端获取体验券店家列表 @Zy")
    public R getCouponShopList(ShopCouponModel shopCouponModel){
        List<Map<String,Object>> couponShopList = shopCouponService.getCouponShopList(shopCouponModel);
        if(couponShopList != null && couponShopList.size() > 0){
            Page page = new Page();
            page = (Page)couponShopList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }
}
