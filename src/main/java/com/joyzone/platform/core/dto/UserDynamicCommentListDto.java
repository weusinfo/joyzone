package com.joyzone.platform.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;


@Configuration
@ApiModel("我的-动态/个人主页返回DTO")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class UserDynamicCommentListDto extends BaseModel{

    public UserDynamicCommentListDto(){

    }

    @ApiModelProperty("动态id")
    private Long dynamicId;

    @ApiModelProperty("父级评论id")
    private Long pid;

    @ApiModelProperty("评论id")
    private Long commentId;

    @ApiModelProperty("评论者id")
    private Long userId;

    @ApiModelProperty("评论者头像")
    private String userPic;

    @ApiModelProperty("评论者昵称")
    private String userName;

    @ApiModelProperty("评论时间")
    private Date createTime;

    @ApiModelProperty("评论内容")
    private String content;

    public Long getUserId() {
        return userId;
    }

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
