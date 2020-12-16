package com.joyzone.platform.module.app.controller;

import com.google.common.collect.Maps;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.UserModel;
import com.joyzone.platform.core.service.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Map;
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
    public R saveUserLngAndLat(@RequestParam("userId") Long userId, @RequestParam("lng") double lng, @RequestParam("lat") double lat){
        userSerivce.saveUserLngAndLat(userId,lng,lat);
        return R.ok("操作成功");
    }


    @ApiOperation("根据ID获取用户信息")
    @PostMapping("getUserInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query")
    })
    public R getUserInfo(@RequestParam("userId") Long userId){
        return R.ok(userSerivce.selectByKey(userId));
    }

    @ApiOperation("添加或修改用户信息")
    @PostMapping("saveUser")
    public R saveUser(UserModel userModel){
    	Map<String,Object> map = Maps.newHashMap();
        if(userModel == null)
            return R.error("参数不能为空");
        int i = userSerivce.saveUser(userModel);
        if(i >0) {
        	userModel = userSerivce.selectByKey(userModel.getId());
        	map.put("message","修改成功！");
        	map.put("userId", userModel.getId());
        	map.put("user", userModel);
        	return R.ok((Object)map);
        }
        return R.error("修改失败！");
    }

}
