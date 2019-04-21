package com.joyzone.platform.core.dto;

import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zy on 2019/4/20.
 */
@ApiModel("参加信息DTO")
public class UserJoinCouponDto extends BaseModel {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户头像")
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
