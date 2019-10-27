package com.joyzone.platform.core.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = ForumModel.TABLE_NAME)
@ApiModel("论坛信息")
public class ForumModel extends BaseModel{

    protected static final String TABLE_NAME = "forum";
    @Id
    @Excel(name = "帖子标识")
    @ApiModelProperty("主键")
    private Long id;

    @Column(name = "user_id")
    @ApiModelProperty("发帖用户ID")
    private Long userId;

    /**
     * 0 建议 1吐槽
     */
    @Excel(name="类型",replace= {"建议_0","吐槽_1"})
    @ApiModelProperty("0.建议,1.吐槽")
    private Integer type;

    /**
     * 点赞数量
     */
    @Excel(name="跟帖人数")
    @Column(name = "point_num")
    @ApiModelProperty("跟帖人数")
    private Integer pointNum;

    /**
     * 状态 0 失效 1 正常
     */
    @ApiModelProperty("0 失效,1 正常")
    private Integer status;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    @Excel(name="创建时间",exportFormat = "yyyy-MM-dd HH:mm")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("发表内容")
    private String content;

    @Transient
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    private Date startTime;

    @Transient
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    private Date endTime;

    //跟帖人数
    @ApiModelProperty("跟帖人数")
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