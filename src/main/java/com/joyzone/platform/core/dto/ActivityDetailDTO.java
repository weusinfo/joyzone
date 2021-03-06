package com.joyzone.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Configuration
@ApiModel("前端聚会详情交互DTO202011")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ActivityDetailDTO extends BaseModel{

    public ActivityDetailDTO(){

    }

    @ApiModelProperty("team聚会表主键")
    private Long teamId;

    @ApiModelProperty("发起者id")
    private Long userId;

    @ApiModelProperty("活动类型")
    private Integer activityType;

    @ApiModelProperty("商家id")
    private Long shopId;

    @ApiModelProperty("商家名称")
    private String shopName;

    @ApiModelProperty("聚会标题")
    private String activityName;

    @ApiModelProperty("聚会时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("活动地点")
    private String activityAddress;

    @ApiModelProperty("人均费用")
    private Integer payWay;

    @ApiModelProperty("男生数量")
    private Integer boyNumber;

    @ApiModelProperty("女生数量")
    private Integer girlNumber;

    @ApiModelProperty("参与人数")
    private Integer joinUserNum;

    @ApiModelProperty("聚会总人数")
    private Integer totalUserNum;

    @ApiModelProperty("参与者头像列表")
    private List<String> joinUserImgs;

    @ApiModelProperty("聚会详情按钮显示文本")
    private String buttonShow;

    @ApiModelProperty("订单状态 0：进行中 1：成功 2：失败 3：已解散")
    private Integer status;

    @ApiModelProperty("订单类型 0:特约 1：好友 2：普通")
    private Integer tag;

    @ApiModelProperty("特约对象的id")
    private Long inviteUserId;

    @ApiModelProperty("特约对象的昵称")
    private String inviteUserName;

    @ApiModelProperty("参与者信息列表")
    private List<ActivityUserDTO> joinUserInfoList;

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

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getBoyNumber() {
        return boyNumber;
    }

    public void setBoyNumber(Integer boyNumber) {
        this.boyNumber = boyNumber;
    }

    public Integer getGirlNumber() {
        return girlNumber;
    }

    public void setGirlNumber(Integer girlNumber) {
        this.girlNumber = girlNumber;
    }

    public Integer getJoinUserNum() {
        return joinUserNum;
    }

    public void setJoinUserNum(Integer joinUserNum) {
        this.joinUserNum = joinUserNum;
    }

    public Integer getTotalUserNum() {
        return totalUserNum;
    }

    public void setTotalUserNum(Integer totalUserNum) {
        this.totalUserNum = totalUserNum;
    }

    public List<String> getJoinUserImgs() {
        return joinUserImgs;
    }

    public void setJoinUserImgs(List<String> joinUserImgs) {
        this.joinUserImgs = joinUserImgs;
    }

    public String getButtonShow() {
        return buttonShow;
    }

    public void setButtonShow(String buttonShow) {
        this.buttonShow = buttonShow;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Long getInviteUserId() {
        return inviteUserId;
    }

    public void setInviteUserId(Long inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    public String getInviteUserName() {
        return inviteUserName;
    }

    public void setInviteUserName(String inviteUserName) {
        this.inviteUserName = inviteUserName;
    }

    public List<ActivityUserDTO> getJoinUserInfoList() {
        return joinUserInfoList;
    }

    public void setJoinUserInfoList(List<ActivityUserDTO> joinUserInfoList) {
        this.joinUserInfoList = joinUserInfoList;
    }
}
