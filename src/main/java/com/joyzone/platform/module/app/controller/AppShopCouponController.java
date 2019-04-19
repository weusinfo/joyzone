package com.joyzone.platform.module.app.controller;


import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.InvitingDto;
import com.joyzone.platform.core.model.InvitingModel;
import com.joyzone.platform.core.service.InvitingService;
import com.joyzone.platform.core.service.ShopCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app_shopcoupon")
@Api(tags = "app商家体验券相关接口",description = "AppShopCouponController")
public class AppShopCouponController {

    @Autowired
    private ShopCouponService shopCouponService;

    /**
     * zy
     */
    /*@PostMapping("saveInviting")
    @ApiOperation("前端发起邀请")
    public R saveInviting(InvitingModel invitingModel){
        return invitingService.saveInviting(invitingModel);
    }*/

    /**
     * zy
     */
    @PostMapping("/getCouponShopList")
    @ApiOperation("前端获取体验券店家列表")
    public R getCouponShopList(InvitingDto invitingDto){
        List<Map<String,Object>> couponShopList = shopCouponService.getCouponShopList(invitingDto);
        if(couponShopList != null && couponShopList.size() > 0){
            Page page = new Page();
            page = (Page)couponShopList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }
}
