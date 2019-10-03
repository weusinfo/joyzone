package com.joyzone.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zy on 2019/4/20.
 */
@ApiModel("店家组队用户参与信息DTO")
@Configuration
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class UserJoinTeamDto extends BaseModel {

    @ApiModelProperty("店家组队ID")
    private Long teamOrInvitingId;

    @ApiModelProperty("0：店家组队订单 1：体验券订单")
    private Integer orderType;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户头像")
    private String userHeadImg;

    public Long getTeamOrInvitingId() {
        return teamOrInvitingId;
    }

    public void setTeamOrInvitingId(Long teamOrInvitingId) {
        this.teamOrInvitingId = teamOrInvitingId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

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
