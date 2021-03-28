package com.joyzone.platform.module.app.controller;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.SystemHobbyDTO;
import com.joyzone.platform.core.model.HobbyModel;
import com.joyzone.platform.core.model.TeamModel;
import com.joyzone.platform.core.service.HobbyService;
import com.joyzone.platform.core.vo.UserHobbyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app_hobby")
@Api(tags = "app用户爱好相关接口",description = "AppHobbyController")
public class AppHobbyController {
    private Logger LOGGER = LoggerFactory.getLogger(AppHobbyController.class);

    @Autowired
    private HobbyService hobbyService;

    @PostMapping("/getSystemHobbyList")
    @ApiOperation("获取系统爱好清单 @zhangyu")
    public R getSystemHobbyList(Long userId){
        List<SystemHobbyDTO> hobbyList =hobbyService.getSystemHobbyList(userId);
        return R.ok(hobbyList);
    }

    @PostMapping("/getHobbyNamesByType")
    @ApiOperation("获取系统爱好清单 @zhangyu")
    public R getHobbyNamesByType(String hobbyType,Long userId){
        List<SystemHobbyDTO> hobbyList =hobbyService.getHobbyNamesByType(hobbyType,userId);
        return R.ok(hobbyList);
    }

    @PostMapping("/saveUserHobbys")
    @ApiOperation("保存爱好清单 @zhangyu")
    public R saveUserHobbys(@RequestBody UserHobbyVO userHobbyVO){
        int ret = hobbyService.saveUserHobbys(userHobbyVO);
        return ret > 0 ? R.ok() : R.error("操作失败");
    }

}
