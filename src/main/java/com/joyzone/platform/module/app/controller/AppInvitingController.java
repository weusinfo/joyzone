package com.joyzone.platform.module.app.controller;


import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.InvitingDto;
import com.joyzone.platform.core.model.InvitingModel;
import com.joyzone.platform.core.service.InvitingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app_inviting")
@Api(tags = "app邀请函相关接口",description = "AppInvitingController")
public class AppInvitingController {

    @Autowired
    private InvitingService invitingService;

    /**
     * Mr.Gx
     */
    @PostMapping("saveInviting")
    @ApiOperation("前端发起邀请")
    public R saveInviting(InvitingModel invitingModel){
        return invitingService.saveInviting(invitingModel);
    }

    /**
     * Mr.Gx
     */
    @PostMapping("getUserToInvitings")
    @ApiOperation("前端获取个人邀请列表")
    public R getUserToInvitings(InvitingDto invitingDto){
        return invitingService.getUserToInvitings(invitingDto);
    }
}
