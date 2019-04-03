package com.joyzone.platform.module.admin.controller;


import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.OrderDto;
import com.joyzone.platform.core.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Api(value="后台订单管理V",description="后台订单管理",tags="1.0")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Mr.Gx
     */
    @GetMapping("getOrderList")
    @ApiOperation("后台订单列表")
    public R getOrderList(OrderDto orderDto){
        return orderService.getOrderList(orderDto);
    }

    /**
     * Mr.Gx
     */
    @GetMapping("findById/{id}")
    @ApiOperation("后台订单列表")
    public R findById(@PathVariable Long id){
        return orderService.findById(id);
    }

    /**
     * Mr.Gx
     */
    @GetMapping("getTeamUsers/{teamId}/{pageNum}/{pageSize}")
    @ApiOperation("后台订单列表")
    public R getTeamUsers(@PathVariable Long teamId,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        return orderService.getTeamUsers(teamId,pageNum,pageSize);
    }
}
