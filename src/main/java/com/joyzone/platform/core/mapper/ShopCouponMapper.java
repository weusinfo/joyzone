package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.InvitingDto;
import com.joyzone.platform.core.model.ShopCouponModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ShopCouponMapper extends Mapper<ShopCouponModel> {

    //后台体验券管理
    List<ShopCouponModel> getShopCouponList(ShopCouponModel shopCouponModel);

    List<Map<String,Object>> getCouponShopList(InvitingDto invitingDto);
}