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
    private Long teamId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户头像")
    private String userHeadImg;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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
