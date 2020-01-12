package com.joyzone.platform.core.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

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
    @ApiModelProperty("店家组队或个人邀请主键")
    private Long teamOrInvitingId;

    //店家ID
    @ApiModelProperty("店家ID")
    private Long shopId;

    //店家图片
    @ApiModelProperty("店家或个人图片")
    private String shopOrUserImg;

    //店家名称
    @ApiModelProperty("店家或个人邀请活动名称")
    private String shopOrContentName;

    //店家地址
    @ApiModelProperty("店家或个人邀请地址")
    private String address;

    //体验活动开始时间
    @ApiModelProperty("店家组队开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    //允许的最大人数
    @ApiModelProperty("允许最大人数")
    private Integer personNum;

    @ApiModelProperty("支付方式")
    private Integer payWay;

    @ApiModelProperty("0：进行中 1：已完成")
    private Integer orderStatus;

    @ApiModelProperty("0：店家组队订单 1：个人邀请订单")
    private Integer orderType;

    private List<UserJoinTeamDto> userJoinList;

    public Long getTeamOrInvitingId() {
        return teamOrInvitingId;
    }

    public void setTeamOrInvitingId(Long teamOrInvitingId) {
        this.teamOrInvitingId = teamOrInvitingId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopOrUserImg() {
        return shopOrUserImg;
    }

    public void setShopOrUserImg(String shopOrUserImg) {
        this.shopOrUserImg = shopOrUserImg;
    }

    public String getShopOrContentName() {
        return shopOrContentName;
    }

    public void setShopOrContentName(String shopOrContentName) {
        this.shopOrContentName = shopOrContentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public List<UserJoinTeamDto> getUserJoinList() {
        return userJoinList;
    }

    public void setUserJoinList(List<UserJoinTeamDto> userJoinList) {
        this.userJoinList = userJoinList;
    }

}
