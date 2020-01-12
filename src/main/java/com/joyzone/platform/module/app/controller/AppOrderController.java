package com.joyzone.platform.module.app.controller;


import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.OrderMineDto;
import com.joyzone.platform.core.mapper.InvitingUserMapper;
import com.joyzone.platform.core.model.*;
import com.joyzone.platform.core.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app_order")
@Api(tags = "app首页订单列表相关接口",description = "AppOrderController")
public class AppOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private TeamUsersService teamUsersService;
    @Autowired
    private CouponUserService couponUserService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ShopCouponService couponService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private InvitingUserService invitingUserService;
    @Autowired
    private InvitingService invitingService;

    @Autowired
    private ChatService chatService;
    /**
     * zy
     */
    /*@PostMapping("/getMyOrderList")
    @ApiOperation("前端获取订单組隊相关列表 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "0:报名 1：成功", required = true, dataType = "Integer", paramType = "query")
    })
    public R getMyOrderList(OrderModel orderModel, Long userId, Integer type){
        PageHelper.startPage(0,10);
        List<OrderMineDto> myOrderList = orderService.getTeamOrderList(orderModel,userId,type);
        if(myOrderList != null && myOrderList.size() > 0){
            Page page = new Page();
            page = (Page)myOrderList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }*/
    @PostMapping("/getMyOrderList")
    @ApiOperation("新版：前端获取订单組隊相关列表 返回值orderType为0：店家组队；1：个人邀请 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "0:我发起 1：我加入", required = true, dataType = "Integer", paramType = "query")
    })
    public R getMyOrderList(OrderModel orderModel, Long userId, Integer type){
        return orderService.getTeamOrderList(orderModel,userId,type);
    }

    /*@PostMapping("/quitTheTeamOrCoupon")
    @ApiOperation("前端用户退出已有组队或取消领取的体验券 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "teamOrCouponId", value = "组队ID或体验券ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "0:店家组队 1:体验券", required = true, dataType = "Integer", paramType = "query")
    })
    public R quitTheTeamOrCoupon(TeamUsersModel model, Long userId, Long teamOrCouponId, Integer type){
        if(type == 0){
            return quitTheTeam(model,userId,teamOrCouponId);
        }
        if(type == 1){
            return quitTheCoupon(model,userId,teamOrCouponId);
        }
        return R.error("type参数有误");
    }

    public R quitTheTeam(TeamUsersModel model, Long userId, Long teamOrCouponId){
        TeamUsersModel teamUsersModel = teamUsersService.checkUserInTeam(model,userId,teamOrCouponId);
        if(teamUsersModel == null){
            return R.error("用户未报名该组队！");
        }
        if(teamUsersModel != null && teamUsersModel.getStatus() == 1){
            return R.error("用户未报名该组队！");
        }
        if(teamUsersModel != null && teamUsersModel.getStatus() == 0){
            Map<String,Object> teamInfo = teamService.checkTeamIfSuccess(teamOrCouponId);
            Integer number = (Integer) teamInfo.get("number");
            Integer joinNum = Integer.parseInt(teamInfo.get("joinNum").toString());
            teamUsersModel.setStatus(1);
            teamUsersModel.setUpdateTime(new Date());
            groupService.cancelGroup(teamUsersModel.getTeamId(), userId);
            int result = teamUsersService.update(teamUsersModel);
            if(result == 1){
                if(number == joinNum){
                    TeamModel teamModel = new TeamModel();
                    teamModel.setId(teamOrCouponId);
                    teamModel.setResult(0);
                    teamModel.setUpdateTime(new Date());
                    teamService.update(teamModel);
                }
                return R.ok("用户退出成功！");
            }else {
                return R.error("用户退出失败！");
            }
        }
        return R.error("未知错误！");
    }

    public R quitTheCoupon(TeamUsersModel model, Long userId, Long teamOrCouponId){
        CouponUserModel couponUserModel = couponUserService.checkUserInCoupon(new CouponUserModel(),userId,teamOrCouponId);
        if(couponUserModel == null){
            return R.error("用户未领取该体验券！");
        }
        if(couponUserModel != null && couponUserModel.getStatus() == 1){
            return R.error("用户未领取该体验券！");
        }
        if(couponUserModel != null && couponUserModel.getStatus() == 0){
            Map<String,Object> couponInfo = couponService.checkCouponIfSuccess(teamOrCouponId);
            Integer number = (Integer) couponInfo.get("number");
            Integer joinNum = Integer.parseInt(couponInfo.get("joinNum").toString());
            couponUserModel.setStatus(1);
            couponUserModel.setUpdateTime(new Date());
            int result = couponUserService.update(couponUserModel);
            if(result == 1){
                if(number == joinNum) {
                    ShopCouponModel couponModel = new ShopCouponModel();
                    couponModel.setId(teamOrCouponId);
                    couponModel.setResult(0);
                    couponModel.setUpdateTime(new Date());
                    couponService.update(couponModel);
                }
                return R.ok("用户取消体验券成功！");
            }else {
                return R.error("用户取消体验券失败！");
            }
        }
        return R.error("未知错误！");
    }*/
    @PostMapping("/quitTheTeamOrInviting")
    @ApiOperation("前端用户退出已有组队 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "teamOrInvitingId", value = "组队ID或个人邀请ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "orderType", value = "0:店家组队 1:个人邀请", required = true, dataType = "Integer", paramType = "query")
    })
    public R quitTheTeamOrInviting(TeamUsersModel model, Long userId, Long teamOrInvitingId, Integer orderType){
        if(orderType == 0){
            return quitTheTeam(model,userId,teamOrInvitingId);
        }
        if(orderType == 1){
            return quitTheInviting(userId,teamOrInvitingId);
        }
        return R.error("type参数有误");
    }

    public R quitTheTeam(TeamUsersModel model, Long userId, Long teamOrInvitingId){
        TeamUsersModel teamUsersModel = teamUsersService.checkUserInTeam(model,userId,teamOrInvitingId);
        if(teamUsersModel == null){
            return R.error("用户未报名该组队！");
        }
        if(teamUsersModel != null && teamUsersModel.getStatus() == 1){
            return R.error("用户未报名该组队！");
        }
        if(teamUsersModel != null && teamUsersModel.getStatus() == 0){
            Map<String,Object> teamInfo = teamService.checkTeamIfSuccess(teamOrInvitingId);
            Integer number = (Integer) teamInfo.get("number");
            Integer joinNum = Integer.parseInt(teamInfo.get("joinNum").toString());
            teamUsersModel.setStatus(1);
            teamUsersModel.setUpdateTime(new Date());
            boolean isOwner = teamService.isTeamOwner(teamUsersModel.getTeamId(), userId);
            if(isOwner) {
                //userId为发起者时，删掉对应的群并吧team的状态要改为已失效，已失败。
                groupService.deleteGroup(teamUsersModel.getTeamId());
                TeamModel teamModel = new TeamModel();
                teamModel.setId(teamOrInvitingId);
                teamModel.setStatus(1);  //组队无效
                teamModel.setResult(2);  //组队失败
                teamModel.setUpdateTime(new Date());
                teamService.update(teamModel);
                return R.ok("用户退出成功！");
            }else {
                //userId为参与者时，退出对应的群。且若number == joinNum时吧team的状态要改为已组队中。
            	groupService.cancelGroup(teamUsersModel.getTeamId(), userId);
                int result = teamUsersService.update(teamUsersModel);
                if(result == 1){
                    if(number == joinNum){
                        TeamModel teamModel = new TeamModel();
                        teamModel.setId(teamOrInvitingId);
                        teamModel.setResult(0);
                        teamModel.setUpdateTime(new Date());
                        teamService.update(teamModel);
                    }
                    return R.ok("用户退出成功！");
                }else {
                    return R.error("用户退出失败！");
                }
            }
        }
        return R.error("未知错误！");
    }

    public R quitTheInviting(Long userId, Long teamOrInvitingId){
        InvitingUserModel invitingUserModel = invitingUserService.checkUserInInviting(new InvitingUserModel(),userId,teamOrInvitingId);
        if(invitingUserModel == null){
            return R.error("用户未加入该个人邀请！");
        }
        if(invitingUserModel != null && invitingUserModel.getStatus() == 1){
            return R.error("用户未加入该个人邀请！");
        }
        if(invitingUserModel != null && invitingUserModel.getStatus() == 0){
            Map<String,Object> invitingInfo = invitingService.checkInvitingIfSuccess(teamOrInvitingId);
            Integer number = (Integer) invitingInfo.get("number");
            Integer joinNum = Integer.parseInt(invitingInfo.get("joinNum").toString());
            invitingUserModel.setStatus(1);
            invitingUserModel.setUpdateTime(new Date());
            //todo 退出个人邀请的群
            String groupId = invitingService.isInvitingOwner(teamOrInvitingId, userId);
            if(StringUtil.isNotEmpty(groupId)) {
                //userId为发起者时，删掉对应的群并吧inviting的状态要改为已失效，已失败。
            	chatService.deleteTeamGroup(groupId);
                InvitingModel invitingModel = new InvitingModel();
                invitingModel.setId(teamOrInvitingId);
                invitingModel.setStatus(1);  //已失效
                invitingModel.setResult(2);  //已失败
                invitingModel.setUpdateTime(new Date());
                invitingService.update(invitingModel);
                return R.ok("用户取消个人邀请成功！");
            }else {
                //userId为参与者时，退出对应的群。且若number == joinNum时吧inviting的状态要改为邀约中。
            	groupId = invitingService.getChatGroupId(teamOrInvitingId);
            	chatService.cancelTeamGroup(groupId, userId);
                /*groupService.cancelGroup(teamUsersModel.getTeamId(), userId);*/
                int result = invitingUserService.update(invitingUserModel);
                if(result == 1){
                    if(number == joinNum) {
                        InvitingModel invitingModel = new InvitingModel();
                        invitingModel.setId(teamOrInvitingId);
                        invitingModel.setResult(2);
                        invitingModel.setUpdateTime(new Date());
                        invitingService.update(invitingModel);
                    }
                    return R.ok("用户取消个人邀请成功！");
                }else {
                    return R.error("用户取消个人邀请失败！");
                }
            }

        }
        return R.error("未知错误！");
    }


}
