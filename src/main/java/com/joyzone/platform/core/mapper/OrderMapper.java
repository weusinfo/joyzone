package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.OrderDto;
import com.joyzone.platform.core.dto.OrderMineDto;
import com.joyzone.platform.core.model.OrderModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMapper extends Mapper<OrderModel> {

    List<OrderDto> getOrderList(OrderDto orderDto);

    OrderDto findById(@Param("id") Long id);

    List<OrderMineDto> getTeamOrderList(OrderModel orderModel);

}