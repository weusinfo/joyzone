package com.joyzone.platform.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = ForumDetailModel.TABLE_NAME)
@ApiModel("论坛跟帖信息")
public class ForumDetailModel {
    protected static final String TABLE_NAME = "forum_detail";

    @Id
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 总评论ID
     */
    @ApiModelProperty("总评论ID")
    @Column(name = "forum_id")
    private Long forumId;

    /**
     * 父级评论
     */
    @ApiModelProperty("父级评论")
    private Long pid;

    @Column(name = "user_id")
    @ApiModelProperty("跟帖人")
    private Long userId;

    @Column(name = "point_num")
    @ApiModelProperty("点赞人数")
    private Integer pointNum;

    /**
     * 0 失效 1正常
     */
    @ApiModelProperty("0 失效 1正常")
    private Integer status;

    @Column(name = "create_time")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @ApiModelProperty("创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取总评论ID
     *
     * @return forum_id - 总评论ID
     */
    public Long getForumId() {
        return forumId;
    }

    /**
     * 设置总评论ID
     *
     * @param forumId 总评论ID
     */
    public void setForumId(Long forumId) {
        this.forumId = forumId;
    }

    /**
     * 获取父级评论
     *
     * @return pid - 父级评论
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父级评论
     *
     * @param pid 父级评论
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return point_num
     */
    public Integer getPointNum() {
        return pointNum;
    }

    /**
     * @param pointNum
     */
    public void setPointNum(Integer pointNum) {
        this.pointNum = pointNum;
    }

    /**
     * 获取0 失效 1正常
     *
     * @return status - 0 失效 1正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 失效 1正常
     *
     * @param status 0 失效 1正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}