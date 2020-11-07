package com.joyzone.platform.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "give_thumb")
public class GiveThumbModel {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 点赞者id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 动态id
     */
    @Column(name = "dynamic_id")
    private Long dynamicId;

    /**
     * 0：点赞 1：取消点赞
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取点赞者id
     *
     * @return user_id - 点赞者id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置点赞者id
     *
     * @param userId 点赞者id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取动态id
     *
     * @return dynamic_id - 动态id
     */
    public Long getDynamicId() {
        return dynamicId;
    }

    /**
     * 设置动态id
     *
     * @param dynamicId 动态id
     */
    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    /**
     * 获取0：点赞 1：取消点赞
     *
     * @return status - 0：点赞 1：取消点赞
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0：点赞 1：取消点赞
     *
     * @param status 0：点赞 1：取消点赞
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}