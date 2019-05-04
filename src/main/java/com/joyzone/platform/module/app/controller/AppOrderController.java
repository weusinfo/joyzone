package com.joyzone.platform.module.app.controller;


import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.OrderMineDto;
import com.joyzone.platform.core.dto.TeamDto;
import com.joyzone.platform.core.model.CouponUserModel;
import com.joyzone.platform.core.model.OrderModel;
import com.joyzone.platform.core.model.TeamModel;
import com.joyzone.platform.core.model.TeamUsersModel;
import com.joyzone.platform.core.service.CouponUserService;
import com.joyzone.platform.core.service.OrderService;
import com.joyzone.platform.core.service.TeamUsersService;
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

    /**
     * zy
     */
    @PostMapping("/getMyOrderList")
    @ApiOperation("前端获取订单組隊相关列表 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "0:报名 1：成功", required = true, dataType = "Integer", paramType = "query")
    })
    public R getMyOrderList(OrderModel orderModel, Long userId, Integer type){
        List<OrderMineDto> myOrderList = orderService.getTeamOrderList(orderModel,userId,type);
        if(myOrderList != null && myOrderList.size() > 0){
            Page page = new Page();
            page = (Page)myOrderList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    @PostMapping("/quitTheTeamOrCoupon")
    @ApiOperation("前端用户退出已有组队或取消领取的体验券 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "teamOrCouponId", value = "组队ID", required = true, dataType = "Long", paramType = "query"),
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
            teamUsersModel.setStatus(1);
            teamUsersModel.setUpdateTime(new Date());
            int result = teamUsersService.update(teamUsersModel);
            if(result == 1){
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
            couponUserModel.setStatus(1);
            couponUserModel.setUpdateTime(new Date());
            int result = couponUserService.update(couponUserModel);
            if(result == 1){
                return R.ok("用户取消体验券成功！");
            }else {
                return R.error("用户取消体验券失败！");
            }
        }
        return R.error("未知错误！");
    }

}
