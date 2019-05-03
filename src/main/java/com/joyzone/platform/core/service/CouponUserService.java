package com.joyzone.platform.core.service;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.CouponUserMapper;
import com.joyzone.platform.core.model.CouponUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CouponUserService extends BaseService<CouponUserModel> {

    @Autowired
    private CouponUserMapper couponUserMapper;

    public CouponUserModel checkUserInCoupon(CouponUserModel model, Long userId, Long couponId){
        return couponUserMapper.checkUserInCoupon(model,userId,couponId);
    }

}
