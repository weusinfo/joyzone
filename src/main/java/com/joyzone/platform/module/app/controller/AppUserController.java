package com.joyzone.platform.module.app.controller;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.service.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:TODO
 *
 * @author Mr.Gx
 * date: 2019/3/29
 */
@RestController
@RequestMapping("app_user")
@Api(description = "AppUserController",tags = "app用户相关接口")
public class AppUserController {

    @Autowired
    private UserSerivce userSerivce;

    @ApiOperation("根据ID保存用户的经纬度信息")
    @PostMapping("saveUserLngAndLat")
    @ApiImplicitParams({
        @ApiImplicitParam(value = "用ID",name = "userId",paramType = "form"),
        @ApiImplicitParam(value = "经度",name = "lng",paramType = "form"),
        @ApiImplicitParam(value = "纬度",name = "lat",paramType = "form")
    })
    public R saveUserLngAndLat(@RequestParam("userId") Long userId,@RequestParam("lng") Double lng,@RequestParam("lat") Double lat){
        userSerivce.saveUserLngAndLat(userId,lng,lat);
        return R.ok("操作成功");
    }
}
