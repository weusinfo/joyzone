package com.joyzone.platform.module.app.controller;


import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.InvitingDto;
import com.joyzone.platform.core.model.InvitingModel;
import com.joyzone.platform.core.model.UserModel;
import com.joyzone.platform.core.service.InvitingService;
import com.joyzone.platform.core.service.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app_inviting")
@Api(tags = "app邀请函相关接口",description = "AppInvitingController")
public class AppInvitingController {

    @Autowired
    private InvitingService invitingService;
    @Autowired
    private UserSerivce userSerivce;

    /**
     * Mr.Gx
     */
    @PostMapping("saveInviting")
    @ApiOperation("发起邀请 @Mr.Gx")
    public R saveInviting(InvitingModel invitingModel){
        if(invitingModel == null)
            return R.error("参数不能为空.");
        if(invitingModel.getOwner() == null)
            return R.error("发起人ID不能为空.");
        if(StringUtils.isBlank(invitingModel.getContent()))
            return R.error("邀请主题不能为空.");
        if(invitingModel.getType() == null)
            return R.error("邀请类型不能为空.");
        if(StringUtils.isBlank(invitingModel.getAddress()))
            return R.error("邀请地址不能为空.");
        if(invitingModel.getStartTime() == null)
            return R.error("主题进行时间不能为空.");
        if(invitingModel.getPayWay() == null)
            return R.error("支付方式不能为空.");
        if(invitingModel.getSexWant() == null)
            return R.error("对象性别选择不能为空.");
        if(invitingModel.getShopId() != null){
            if(StringUtils.isBlank(invitingModel.getShopName())){
                return R.error("店家名称不能为空.");
            }
        }
        UserModel userModel = userSerivce.selectByKey(invitingModel.getOwner());
        if(userModel ==null || userModel.getSex() == null || userModel.getUserName() == null || userModel.getBirthday() == null){
            return R.error(100,"请完善个人必要信息：昵称/性别/生日");
        }
        return invitingService.saveInviting(invitingModel);
    }

    /**
     * Mr.Gx
     */
    @PostMapping("getUserToInvitings")
    @ApiOperation("获取受邀列表   @Mr.Gx")
    public R getUserToInvitings(InvitingDto invitingDto){
        return invitingService.getUserToInvitings(invitingDto);
    }

    /**
     * Mr.Gx
     */
    @PostMapping("getConfirmInvitings")
    @ApiOperation("获取我的受邀正式函列表 @Mr.Gx")
    public R getConfirmInvitings(InvitingDto invitingDto){
        return invitingService.getConfirmInvitings(invitingDto);
    }

    /**
     * Mr.Gx
     */
    @PostMapping("getMyInvitings")
    @ApiOperation("获取我的邀请列表 @Mr.Gx")
    public R getMyInvitings(InvitingDto invitingDto){
        return invitingService.getMyInvitings(invitingDto);
    }

    /**
     * Mr.Gx
     */
    @PostMapping("getRespondInvitings")
    @ApiOperation("获取回应我的邀请列表 @Mr.Gx")
    public R getRespondInvitings(InvitingDto invitingDto){
        return invitingService.getRespondInvitings(invitingDto);
    }

    @PostMapping("agreeOrNotTheInviting")
    @ApiOperation("受邀列表：用户同意或拒绝某条邀约 @zy")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invitingId", value = "邀约id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "0 拒绝 1 接受", required = true, dataType = "Integer", paramType = "query")
    })
    public R agreeOrNotTheInviting(InvitingDto invitingDto,@RequestParam("type") Integer type){
        if(invitingDto == null || invitingDto.getInvitingId() == null || invitingDto.getUserId() == null){
            return R.error("参数不能为空！");
        }
        UserModel userModel = userSerivce.selectByKey(invitingDto.getUserId());
        if(userModel ==null || userModel.getSex() == null || userModel.getUserName() == null || userModel.getBirthday() == null){
            return R.error(100,"请完善个人必要信息：昵称/性别/生日");
        }
        InvitingModel invitingModel = invitingService.selectByKey(invitingDto.getInvitingId());
        if(invitingModel == null || invitingModel.getStatus() == 1){
            return R.error("该邀约不存在或已失效！");
        }
        int ret = invitingService.agreeOrNotTheInviting(invitingDto,type);
        if(ret == 0){
            return R.error("操作失败！");
        }
        return R.ok("操作成功！");
    }

    @PostMapping("sendFinalInviting")
    @ApiOperation("回函：邀请用户正式参与活动 @zy")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invitingId", value = "邀约id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "发起邀约者ID", required = true, dataType = "Long", paramType = "query")
    })
    public R sendFinalInviting(InvitingDto invitingDto){
        if(invitingDto == null || invitingDto.getInvitingId() == null || invitingDto.getUserId() == null){
            return R.error("参数不能为空！");
        }
        UserModel userModel = userSerivce.selectByKey(invitingDto.getUserId());
        if(userModel ==null || userModel.getSex() == null || userModel.getUserName() == null || userModel.getBirthday() == null){
            return R.error(100,"请完善个人必要信息：昵称/性别/生日");
        }
        InvitingModel invitingModel = invitingService.selectByKey(invitingDto.getInvitingId());
        if(invitingModel == null || invitingModel.getStatus() == 1){
            return R.error("该邀约不存在或已失效！");
        }
        int ret = invitingService.sendFinalInviting(invitingDto);
        if(ret == 0){
            return R.error("邀请失败！");
        }
        return R.ok("邀请成功！");
    }


}
