package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.CouponUserModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;

@Resource
public interface CouponUserMapper extends Mapper<CouponUserModel> {

    CouponUserModel checkUserInCoupon(CouponUserModel model, @Param("userId") Long userId, @Param("couponId") Long couponId);

}