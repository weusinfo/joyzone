package com.joyzone.platform.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.vo.LocationVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;


@Configuration
@ApiModel("动态模块动态列表返回DTO")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class IndexDynamicListDto extends BaseModel{

    public IndexDynamicListDto(){

    }

    @ApiModelProperty("动态id")
    private Long dynamicId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户头像")
    private String userPic;

    @ApiModelProperty("用户昵称")
    private String userName;

    @ApiModelProperty("用户性别")
    private int userSex;

    @ApiModelProperty("用户年龄")
    private int userAge;

    @ApiModelProperty("动态内容")
    private String dynamicContent;

    @ApiModelProperty("关注人数")
    private int followCount;

    @ApiModelProperty("评论数量")
    private int commentCount;

    @ApiModelProperty("点赞数量")
    private int thumbCount;

    @ApiModelProperty("是否点赞 0：未点赞 1：已点赞")
    private int thumbed;

    @ApiModelProperty("是否关注 0：未关注 1：已关注")
    private int followed;

    @ApiModelProperty("经度")
    private double lnt;

    @ApiModelProperty("纬度")
    private double lat;

    @ApiModelProperty("距离")
    private double distance;

    private String pics;

    @ApiModelProperty("图片地址")
    private List<String> dynamicPics;

    @ApiModelProperty("评论详情列表")
    private List<UserDynamicCommentListDto> commentDetailList;

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getThumbCount() {
        return thumbCount;
    }

    public void setThumbCount(int thumbCount) {
        this.thumbCount = thumbCount;
    }

    public int getThumbed() {
        return thumbed;
    }

    public void setThumbed(int thumbed) {
        this.thumbed = thumbed;
    }

    public int getFollowed() {
        return followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }

    public List<UserDynamicCommentListDto> getCommentDetailList() {
        return commentDetailList;
    }

    public void setCommentDetailList(List<UserDynamicCommentListDto> commentDetailList) {
        this.commentDetailList = commentDetailList;
    }

    public double getLnt() {
        return lnt;
    }

    public void setLnt(double lnt) {
        this.lnt = lnt;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public List<String> getDynamicPics() {
        return dynamicPics;
    }

    public void setDynamicPics(List<String> dynamicPics) {
        this.dynamicPics = dynamicPics;
    }
}
