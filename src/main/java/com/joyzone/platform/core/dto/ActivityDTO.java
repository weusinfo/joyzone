package com.joyzone.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Configuration
@ApiModel("前端聚会列表交互DTO202011")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ActivityDTO extends BaseModel{

    public ActivityDTO(){

    }

    @ApiModelProperty("team聚会表主键")
    private Long teamId;

    @ApiModelProperty("发起者id")
    private Long userId;

    @ApiModelProperty("发起者昵称")
    private String userName;

    @ApiModelProperty("发起者封面")
    private String userCover;

    @ApiModelProperty("发起者性别")
    private Integer userSex;

    @ApiModelProperty("发起者年龄")
    private Integer userAge;

    @ApiModelProperty("聚会标题")
    private String activityName;

    @ApiModelProperty("活动地点")
    private String activityAddress;

    @ApiModelProperty("聚会时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("距离")
    private BigDecimal distance;

    @ApiModelProperty("活动类型")
    private Integer activityType;

    @ApiModelProperty("商家价格")
    private BigDecimal price;

    @ApiModelProperty("人均费用")
    private Integer payWay;

    @ApiModelProperty("参与人数")
    private Integer joinUserNum;

    @ApiModelProperty("聚会总人数")
    private Integer totalUserNum;

    @ApiModelProperty("参与者头像列表")
    private List<String> joinUserImgs;

    @ApiModelProperty("特约或好友标志")
    private String label;

    @ApiModelProperty("订单状态 0：进行中 1：成功 2：失败 3：已解散")
    private Integer teamStatus;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCover() {
        return userCover;
    }

    public void setUserCover(String userCover) {
        this.userCover = userCover;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(Integer teamStatus) {
        this.teamStatus = teamStatus;
    }
}
