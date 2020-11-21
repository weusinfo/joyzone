package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.OrderDTO;
import com.joyzone.platform.core.dto.OrderMineDTO;
import com.joyzone.platform.core.model.OrderModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMapper extends Mapper<OrderModel> {

    List<OrderDTO> getOrderList(OrderDTO orderDto);

    OrderDTO findById(@Param("id") Long id);

    List<OrderMineDTO> getTeamOrderList(OrderModel orderModel, @Param("userId") Long userId, @Param("type") Integer type);

    int delOrders(@Param("ids") Long[] ids);
}