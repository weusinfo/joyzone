package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.CouponDTO;
import com.joyzone.platform.core.dto.CouponRuleDTO;
import com.joyzone.platform.core.model.ShopCouponModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ShopCouponMapper extends Mapper<ShopCouponModel> {

    //后台体验券管理
    List<ShopCouponModel> getShopCouponList(ShopCouponModel shopCouponModel);

    List<Map<String,Object>> getCouponShopList(ShopCouponModel shopCouponModel);

    List<CouponDTO> getCouponList(ShopCouponModel shopCouponModel, @Param("userId") Long userId, @Param("sort") Integer sort);

    Map<String,Object> checkCouponIfSuccess(@Param("couponId") Long couponId);

    int delCoupons(@Param("ids")Long[] ids);

    CouponRuleDTO getCouponRuleInfo(@Param("couponId") Long couponId, @Param("userId") Long userId);
    
    int updChatGroupId(@Param("groupId") String groupId, @Param("couponId") Long couponId);
    
    Map<String, Object> getCouponInfo(@Param("couponId") Long couponId);
}