package com.joyzone.platform.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
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

    public List<UserDynamicCommentListDto> getCommentDetailList() {
        return commentDetailList;
    }

    public void setCommentDetailList(List<UserDynamicCommentListDto> commentDetailList) {
        this.commentDetailList = commentDetailList;
    }
}
