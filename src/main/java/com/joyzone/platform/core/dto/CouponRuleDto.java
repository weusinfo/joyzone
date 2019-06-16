package com.joyzone.platform.core.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.common.utils.DateUtils;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Configuration
@ApiModel("前端店家组队列表交互DTO")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class CouponRuleDto extends BaseModel{

    public CouponRuleDto(){

    }

    //体验券ID
    @ApiModelProperty("体验券ID")
    private Long couponId;

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

    //店家价格
    @ApiModelProperty("店家价格")
    private BigDecimal shopPrice;

    //店家电话
    @ApiModelProperty("店家电话")
    private String shopPhone;

    //活动内容
    @ApiModelProperty("活动内容")
    private String couponContent;

    //体验活动开始时间
    @ApiModelProperty("体验券开始时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date startTime;

    //允许的最大人数
    @ApiModelProperty("允许最大人数")
    private Integer personNum;

    @ApiModelProperty("店家收藏状态 0：收藏 1：未收藏")
    private Integer shopCollectStatus;

    private List<UserJoinCouponDto> userJoinList;

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
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

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getCouponContent() {
        return couponContent;
    }

    public void setCouponContent(String couponContent) {
        this.couponContent = couponContent;
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

    public Integer getShopCollectStatus() {
        return shopCollectStatus;
    }

    public void setShopCollectStatus(Integer shopCollectStatus) {
        this.shopCollectStatus = shopCollectStatus;
    }

    public List<UserJoinCouponDto> getUserJoinList() {
        return userJoinList;
    }

    public void setUserJoinList(List<UserJoinCouponDto> userJoinList) {
        this.userJoinList = userJoinList;
    }
}
