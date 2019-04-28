package com.joyzone.platform.module.app.controller;


import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.OrderMineDto;
import com.joyzone.platform.core.dto.TeamDto;
import com.joyzone.platform.core.model.OrderModel;
import com.joyzone.platform.core.model.TeamModel;
import com.joyzone.platform.core.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app_order")
@Api(tags = "app首页订单列表相关接口",description = "AppOrderController")
public class AppOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * zy
     */
    @PostMapping("/getMyOrderList")
    @ApiOperation("前端获取店家組隊列表")
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

}
