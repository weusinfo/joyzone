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
    @ApiOperation("发起邀请")
    public R saveInviting(InvitingModel invitingModel){
        return invitingService.saveInviting(invitingModel);
    }

    /**
     * Mr.Gx
     */
    @PostMapping("getUserToInvitings")
    @ApiOperation("获取受邀列表")
    public R getUserToInvitings(InvitingDto invitingDto){
        return invitingService.getUserToInvitings(invitingDto);
    }

    /**
     * Mr.Gx
     */
    @PostMapping("getConfirmInvitings")
    @ApiOperation("获取我的受邀正式函列表")
    public R getConfirmInvitings(InvitingDto invitingDto){
        return invitingService.getConfirmInvitings(invitingDto);
    }

    /**
     * Mr.Gx
     */
    @PostMapping("getMyInvitings")
    @ApiOperation("获取我的邀请列表")
    public R getMyInvitings(InvitingDto invitingDto){
        return invitingService.getMyInvitings(invitingDto);
    }

    /**
     * Mr.Gx
     */
    @PostMapping("getRespondInvitings")
    @ApiOperation("获取回应我的邀请列表")
    public R getRespondInvitings(InvitingDto invitingDto){
        return invitingService.getRespondInvitings(invitingDto);
    }

}
