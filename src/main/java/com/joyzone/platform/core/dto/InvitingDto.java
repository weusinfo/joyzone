package com.joyzone.platform.core.dto;


import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("受邀函入参DTO")
public class InvitingDto extends BaseModel {

    @ApiModelProperty("邀请函主键")
    private Long invitingId;
    //用户ID
    @ApiModelProperty("用户ID")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInvitingId() {
        return invitingId;
    }

    public void setInvitingId(Long invitingId) {
        this.invitingId = invitingId;
    }
}
