package com.joyzone.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel("App评论")
public class ShopCommentDTO {


    @ApiModelProperty("评论者id")
    private Long userId;

    @ApiModelProperty("商家id")
    private Long shopId;

    @ApiModelProperty("类型")
    private Byte type;

    @ApiModelProperty("是否匿名")
    private boolean isAnonym;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论相片地址")
    private String picUrls;

    @ApiModelProperty("环境类型")
    private Byte environmentType;

    @ApiModelProperty("服务类型")
    private Byte serviceType;

    @ApiModelProperty("体验")
    private Byte playType;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public boolean getIsAnonym() {
        return isAnonym;
    }

    public void setIsAnonym(boolean isAnonym) {
        this.isAnonym = isAnonym;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }

    public Byte getEnvironmentType() {
        return environmentType;
    }

    public void setEnvironmentType(Byte environmentType) {
        this.environmentType = environmentType;
    }

    public Byte getServiceType() {
        return serviceType;
    }

    public void setServiceType(Byte serviceType) {
        this.serviceType = serviceType;
    }

    public Byte getPlayType() {
        return playType;
    }

    public void setPlayType(Byte playType) {
        this.playType = playType;
    }
}
