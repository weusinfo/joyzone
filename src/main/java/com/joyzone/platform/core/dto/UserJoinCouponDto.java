package com.joyzone.platform.core.dto;

import com.joyzone.platform.core.model.BaseModel;

/**
 * Created by zy on 2019/4/20.
 */
public class UserJoinCouponDto extends BaseModel {

    private Long userId;

    private String userHeadImg;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }
}
