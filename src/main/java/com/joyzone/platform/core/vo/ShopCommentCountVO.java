package com.joyzone.platform.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel("App评论列表")
public class ShopCommentCountVO {

    @ApiModelProperty("环境很好")
    private Integer environmentFineCount;

    @ApiModelProperty("环境中等")
    private Integer environmentMiddlingCount;

    @ApiModelProperty("环境很差")
    private Integer environmentLowerCount;

    @ApiModelProperty("服务很好")
    private Integer serviceFineCount;

    @ApiModelProperty("服务中等")
    private Integer serviceMiddlingCount;

    @ApiModelProperty("服务很差")
    private Integer serviceLowerCount;

    @ApiModelProperty("体验很好")
    private Integer playFineCount;

    @ApiModelProperty("服务中等")
    private Integer playMiddlingCount;

    @ApiModelProperty("服务很差")
    private Integer playLowerCount;

    @ApiModelProperty("评论总条数")
    private Integer commentTotal;


    public Integer getEnvironmentFineCount() {
        return environmentFineCount;
    }

    public void setEnvironmentFineCount(Integer environmentFineCount) {
        this.environmentFineCount = environmentFineCount;
    }

    public Integer getEnvironmentMiddlingCount() {
        return environmentMiddlingCount;
    }

    public void setEnvironmentMiddlingCount(Integer environmentMiddlingCount) {
        this.environmentMiddlingCount = environmentMiddlingCount;
    }

    public Integer getEnvironmentLowerCount() {
        return environmentLowerCount;
    }

    public void setEnvironmentLowerCount(Integer environmentLowerCount) {
        this.environmentLowerCount = environmentLowerCount;
    }

    public Integer getServiceFineCount() {
        return serviceFineCount;
    }

    public void setServiceFineCount(Integer serviceFineCount) {
        this.serviceFineCount = serviceFineCount;
    }

    public Integer getServiceMiddlingCount() {
        return serviceMiddlingCount;
    }

    public void setServiceMiddlingCount(Integer serviceMiddlingCount) {
        this.serviceMiddlingCount = serviceMiddlingCount;
    }

    public Integer getServiceLowerCount() {
        return serviceLowerCount;
    }

    public void setServiceLowerCount(Integer serviceLowerCount) {
        this.serviceLowerCount = serviceLowerCount;
    }

    public Integer getPlayFineCount() {
        return playFineCount;
    }

    public void setPlayFineCount(Integer playFineCount) {
        this.playFineCount = playFineCount;
    }

    public Integer getPlayMiddlingCount() {
        return playMiddlingCount;
    }

    public void setPlayMiddlingCount(Integer playMiddlingCount) {
        this.playMiddlingCount = playMiddlingCount;
    }

    public Integer getPlayLowerCount() {
        return playLowerCount;
    }

    public void setPlayLowerCount(Integer playLowerCount) {
        this.playLowerCount = playLowerCount;
    }

    public Integer getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(Integer commentTotal) {
        this.commentTotal = commentTotal;
    }
}
