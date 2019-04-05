package com.joyzone.platform.module.admin.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.OrderDto;
import com.joyzone.platform.core.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

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
    @ApiOperation("后台订单详情")
    public R findById(@PathVariable Long id){
        return orderService.findById(id);
    }

    /**
     * Mr.Gx
     */
    @GetMapping("getTeamUsers/{teamId}/{pageNum}/{pageSize}")
    @ApiOperation("后台订单获取参加人员列表")
    public R getTeamUsers(@PathVariable Long teamId,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        return orderService.getTeamUsers(teamId,pageNum,pageSize);
    }

    @GetMapping("/exportOrderXls")
    @ApiOperation("订单清单导出")
    public void exportShopCouponXls(OrderDto orderDto, HttpServletResponse response) throws Exception{
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(Constants.JOY_ORDER, "UTF-8") + ".xls");
        response.setCharacterEncoding("UTF-8");
        List<OrderDto> list = orderService.selectOrderList(orderDto);;
        ExportParams params = new ExportParams(Constants.JOY_ORDER, Constants.JOY_ORDER);
        Workbook workbook = ExcelExportUtil.exportExcel(params, OrderDto.class, list);
        workbook.write(response.getOutputStream());
    }
}
