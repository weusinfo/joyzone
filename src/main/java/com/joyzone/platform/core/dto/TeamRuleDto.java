package com.joyzone.platform.core.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.common.utils.DateUtils;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Configuration
@ApiModel("前端店家组队列表交互DTO")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class TeamRuleDto extends BaseModel{

    public TeamRuleDto(){

    }

    //体验券ID
    @ApiModelProperty("店家组队主键")
    private Long teamId;

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

    @ApiModelProperty("经度")
    private BigDecimal lng;

    @ApiModelProperty("纬度")
    private BigDecimal lat;

    //店家价格
    @ApiModelProperty("店家价格")
    private BigDecimal shopPrice;

    //店家电话
    @ApiModelProperty("店家电话")
    private String shopPhone;

    //支付方式
    @ApiModelProperty("支付方式")
    private Integer payWay;

    //性别比例
    @ApiModelProperty("性别比例")
    private Integer sexWant;

    //体验活动开始时间
    @ApiModelProperty("店家组队开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = DateUtils.DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date startTime;

    //允许的最大人数
    @ApiModelProperty("允许最大人数")
    private Integer personNum;

    @ApiModelProperty("店家收藏状态 0：收藏 1：未收藏")
    private Integer shopCollectStatus;

    private List<UserJoinTeamDto> userJoinList;

    @ApiModelProperty("店家轮播图片")
    private List<String> coverImgList;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
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

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getSexWant() {
        return sexWant;
    }

    public void setSexWant(Integer sexWant) {
        this.sexWant = sexWant;
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

    public List<UserJoinTeamDto> getUserJoinList() {
        return userJoinList;
    }

    public void setUserJoinList(List<UserJoinTeamDto> userJoinList) {
        this.userJoinList = userJoinList;
    }

    public List<String> getCoverImgList() {
        return coverImgList;
    }

    public void setCoverImgList(List<String> coverImgList) {
        this.coverImgList = coverImgList;
    }

}
