package com.joyzone.platform.module.app.controller;


import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.CouponDto;
import com.joyzone.platform.core.dto.InvitingDto;
import com.joyzone.platform.core.model.InvitingModel;
import com.joyzone.platform.core.model.ShopCouponModel;
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
    @PostMapping("/getCouponList")
    @ApiOperation("前端获取体验券列表  @Yz")
    public R getCouponList(ShopCouponModel shopCouponModel){
        List<CouponDto> couponDtoList = shopCouponService.getCouponList(shopCouponModel);
        if(couponDtoList != null && couponDtoList.size() > 0){
            Page page = new Page();
            page = (Page)couponDtoList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    /**
     * zy
     */
    @PostMapping("/getCouponShopList")
    @ApiOperation("前端获取体验券店家列表 @Yz")
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
