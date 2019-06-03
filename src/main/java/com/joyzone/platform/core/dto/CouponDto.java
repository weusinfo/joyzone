package com.joyzone.platform.core.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.common.utils.DateUtils;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;


import java.util.Date;
import java.util.List;


@Configuration
@ApiModel("前端体验券交互DTO")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class CouponDto extends BaseModel{

    public CouponDto(){

    }

    //体验券ID
    @ApiModelProperty("体验券主键")
    private Long couponId;

    //体验券图片：上面标明体验券价格，后台新增体验券时添加
    @ApiModelProperty("体验券图片")
    private String couponImg;

    //店家ID
    @ApiModelProperty("店家ID")
    private Long shopId;

    //店家图片
    @ApiModelProperty("店家图片")
    private String shopImg;

    //店家名称
    @ApiModelProperty("店家名称")
    private String shopName;

    //体验券名称，或体验活动名称
    @ApiModelProperty("体验券或体验活动名称")
    private String couponName;

    //店家地址
    @ApiModelProperty("店家地址")
    private String shopAddress;

    //体验活动开始时间
    @ApiModelProperty("体验券开始时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date startTime;

    //体验活动结束时间
    @ApiModelProperty("体验券结束时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date endTime;

    //店家类型名称
    @ApiModelProperty("店家类型名称")
    private String shopTypeName;

    //允许的最大人数
    @ApiModelProperty("允许最大人数")
    private Integer personNum;

    private List<UserJoinCouponDto> userJoinList;

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponImg() {
        return couponImg;
    }

    public void setCouponImg(String couponImg) {
        this.couponImg = couponImg;
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

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getShopTypeName() {
        return shopTypeName;
    }

    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public List<UserJoinCouponDto> getUserJoinList() {
        return userJoinList;
    }

    public void setUserJoinList(List<UserJoinCouponDto> userJoinList) {
        this.userJoinList = userJoinList;
    }

}
