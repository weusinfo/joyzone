package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.CouponDto;
import com.joyzone.platform.core.dto.InvitingDto;
import com.joyzone.platform.core.model.ShopCouponModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ShopCouponMapper extends Mapper<ShopCouponModel> {

    //后台体验券管理
    List<ShopCouponModel> getShopCouponList(ShopCouponModel shopCouponModel);

    List<Map<String,Object>> getCouponShopList(ShopCouponModel shopCouponModel);

    List<CouponDto> getCouponList(ShopCouponModel shopCouponModel, @Param("sort") Integer sort);

    Map<String,Object> checkCouponIfSuccess(@Param("couponId") Long couponId);

    int delCoupons(@Param("ids")Long[] ids);
}