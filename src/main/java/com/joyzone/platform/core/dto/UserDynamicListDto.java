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
@ApiModel("我的-动态/个人主页返回DTO")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class UserDynamicListDto extends BaseModel{

    public UserDynamicListDto(){

    }

    @ApiModelProperty("动态id")
    private Long dynamicId;

    @ApiModelProperty("动态内容")
    private String dynamicContent;

    @ApiModelProperty("发动态时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("评论详情列表")
    private String pics;

    @ApiModelProperty("评论详情列表")
    private List<String> dynamicPics;

    @ApiModelProperty("点赞数量")
    private int thumbCount;

    private int kind;

    @ApiModelProperty("评论详情列表")
    private List<UserDynamicCommentListDTO> commentDetailList;

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<UserDynamicCommentListDTO> getCommentDetailList() {
        return commentDetailList;
    }

    public void setCommentDetailList(List<UserDynamicCommentListDTO> commentDetailList) {
        this.commentDetailList = commentDetailList;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public int getThumbCount() {
        return thumbCount;
    }

    public void setThumbCount(int thumbCount) {
        this.thumbCount = thumbCount;
    }

    public List<String> getDynamicPics() {
        return dynamicPics;
    }

    public void setDynamicPics(List<String> dynamicPics) {
        this.dynamicPics = dynamicPics;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
