package com.joyzone.platform.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = ForumModel.TABLE_NAME)
public class ForumModel extends BaseModel{

    protected static final String TABLE_NAME = "forum";
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 0 建议 1吐槽
     */
    private Integer type;

    /**
     * 点赞数量
     */
    @Column(name = "point_num")
    private Integer pointNum;

    /**
     * 状态 0 失效 1 正常
     */
    private Integer status;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private String content;

    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    //跟帖人数
    private Integer forumNum;

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
     * 获取0 建议 1吐槽
     *
     * @return type - 0 建议 1吐槽
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0 建议 1吐槽
     *
     * @param type 0 建议 1吐槽
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取点赞数量
     *
     * @return point_num - 点赞数量
     */
    public Integer getPointNum() {
        return pointNum;
    }

    /**
     * 设置点赞数量
     *
     * @param pointNum 点赞数量
     */
    public void setPointNum(Integer pointNum) {
        this.pointNum = pointNum;
    }

    /**
     * 获取状态 0 失效 1 正常
     *
     * @return status - 状态 0 失效 1 正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0 失效 1 正常
     *
     * @param status 状态 0 失效 1 正常
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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
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

    public Integer getForumNum() {
        return forumNum;
    }

    public void setForumNum(Integer forumNum) {
        this.forumNum = forumNum;
    }
}