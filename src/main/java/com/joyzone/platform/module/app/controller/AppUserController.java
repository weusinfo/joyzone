package com.joyzone.platform.module.app.controller;

import com.google.common.collect.Maps;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.UserModel;
import com.joyzone.platform.core.service.CacheService;
import com.joyzone.platform.core.service.FollowsSerivce;
import com.joyzone.platform.core.service.TeamService;
import com.joyzone.platform.core.service.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
    
    @Autowired
    private CacheService cacheService;
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private FollowsSerivce followsSerivce;

    @ApiOperation("根据ID保存用户的经纬度信息")
    @PostMapping("saveUserLngAndLat")
    public R saveUserLngAndLat(@RequestParam("userId") Long userId, @RequestParam("lng") double lng, @RequestParam("lat") double lat){
        userSerivce.saveUserLngAndLat(userId,lng,lat);
        return R.ok("操作成功");
    }


    @ApiOperation("根据ID获取用户信息")
    @PostMapping("getUserInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户或聊天群ID", required = true, dataType = "String", paramType = "query")
    })
    public R getUserInfo(@RequestParam("userId") String userId){
    	if(StringUtils.isNotEmpty(userId)) {
    		if(userId.startsWith("group_")) {
    			String chatGroupId = userId.substring(6);
    			String headImg = teamService.getTeamHeadImg(chatGroupId);
    			UserModel user = new UserModel();
    			user.setHeadPic(headImg);
    			return R.ok(user);
    			
    		}
    	}
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
    
    @ApiOperation("修改用户封面（动态封面，组队聚会封面）")
    @PostMapping("/changeCover")
    public R changeCover(@RequestParam("userId") Long userId, @RequestParam("coverUrl") String coverUrl) {
    	Map<String,Object> map = Maps.newHashMap();
    	int i = userSerivce.changeCover(userId, coverUrl);
    	if(i >0) {
        	UserModel userModel = userSerivce.selectByKey(userId);
        	if(userModel != null) {
        		userModel.setCoverPic(coverUrl);
            	cacheService.apdUser(userModel);
        		map.put("userId", userModel.getId());
        		map.put("user", userModel);
        		return R.ok(map);
        	}
    	}
    	return R.error("修改失败！");
    }
    
    @ApiOperation("拉黑（恢复）用户,实际上该接口和关注接口相同，不同的是对status的处理")
    @PostMapping("/blockUser")
    public R blockUser(@ApiParam("当前登陆用户ID") @RequestParam("userId") Long userId, @ApiParam("对方用户ID") @RequestParam("targetId") Long targetId, @ApiParam("1:取消拉黑;2: 拉黑用户;") @RequestParam("status") Integer status) {
    	if(status != null && (status == 1 || status == 2)) {
    		followsSerivce.blockUser(userId, targetId, status);
    		if(status == 2) {
    			return R.ok("拉黑用户成功！");
    		}else if(status == 1) {
    			return R.ok("取消拉黑用户成功!");
    		}
    	}
    	return R.error("参数错误！");
    }

}
