package com.joyzone.platform.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;


@Configuration
@ApiModel("前端订单报名成功列表交互DTO")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class OrderMineDto extends BaseModel{

    public OrderMineDto(){

    }

    //体验券ID
    @ApiModelProperty("店家组队或体验券主键")
    private Long teamOrCouponId;

    @ApiModelProperty("0：店家组队订单 1：体验券订单")
    private Integer orderType;

    //店家ID
    @ApiModelProperty("店家ID")
    private Long shopId;

    //店家图片
    @ApiModelProperty("店家图片")
    private String shopImg;

    //店家名称
    @ApiModelProperty("店家名称")
    private String shopName;

    //店家地址
    @ApiModelProperty("店家地址")
    private String shopAddress;

    //体验活动开始时间
    @ApiModelProperty("店家组队开始时间")
    private Date startTime;

    //允许的最大人数
    @ApiModelProperty("允许最大人数")
    private Integer personNum;

    private List<UserJoinTeamDto> userJoinList;

    public Long getTeamOrCouponId() {
        return teamOrCouponId;
    }

    public void setTeamOrCouponId(Long teamOrCouponId) {
        this.teamOrCouponId = teamOrCouponId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public List<UserJoinTeamDto> getUserJoinList() {
        return userJoinList;
    }

    public void setUserJoinList(List<UserJoinTeamDto> userJoinList) {
        this.userJoinList = userJoinList;
    }

}
