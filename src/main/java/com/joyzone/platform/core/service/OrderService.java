package com.joyzone.platform.core.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.OrderDto;
import com.joyzone.platform.core.dto.OrderMineDto;
import com.joyzone.platform.core.dto.TeamDto;
import com.joyzone.platform.core.mapper.OrderMapper;
import com.joyzone.platform.core.mapper.TeamUsersMapper;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.model.OrderModel;
import com.joyzone.platform.core.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService extends BaseService<OrderModel> {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TeamUsersMapper teamUsersMapper;

    /**
     * 后台获取订单列表
     * Mr.Gx
     */
    public R getOrderList(OrderDto orderDto){
        if(orderDto.getPageNum() == null)
            orderDto.setPageNum(BaseModel.PAGE_NUM);
        if(orderDto.getPageSize() == null)
            orderDto.setPageNum(BaseModel.PAGE_SIZE);

        List<OrderDto> list = selectOrderList(orderDto);
        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return  R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    /**
     * 后台获取订单详情
     * Mr.Gx
     */
    public R findById(Long id) {
        if (id == null) {
            return R.error(R.STATUS_FAIL, "订单ID不能为空.");
        }
        return R.ok(orderMapper.findById(id));
    }

    /**
     * 后台获取订单参加的用户清单
     * Mr.Gx
     */
    public R getTeamUsers(Long teamId,Integer pageNum,Integer pageSize){
        if (teamId == null)
            return R.error(R.STATUS_FAIL, "组队标识不能为空.");
        if (pageNum == null)
            pageNum = BaseModel.PAGE_NUM;
        if (pageSize == null)
            pageNum = BaseModel.PAGE_SIZE;

        List<UserModel> list = teamUsersMapper.getTeamUsers(teamId,pageNum,pageSize);
        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return  R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    public  List<OrderDto> selectOrderList(OrderDto orderDto){
        return orderMapper.getOrderList(orderDto);
    }

    private R pageToRet(List<OrderMineDto> list){
        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }
    public R getTeamOrderList(OrderModel orderModel, Long userId, Integer type){
        PageHelper.startPage(orderModel.getPageNum(),orderModel.getPageSize());
        return pageToRet(orderMapper.getTeamOrderList(orderModel,userId,type));
    }

    public int delOrders(Long[] ids){
        return orderMapper.delOrders(ids);
    }
}
