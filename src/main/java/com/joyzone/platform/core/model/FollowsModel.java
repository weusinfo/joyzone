package com.joyzone.platform.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "follows")
public class FollowsModel {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 关注者id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 被关注者id
     */
    @Column(name = "target_id")
    private Long targetId;

    /**
     * 0：关注 1取消关注
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
     * 获取关注者id
     *
     * @return user_id - 关注者id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置关注者id
     *
     * @param userId 关注者id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取被关注者id
     *
     * @return target_id - 被关注者id
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * 设置被关注者id
     *
     * @param targetId 被关注者id
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * 获取0：关注 1取消关注
     *
     * @return status - 0：关注 1取消关注
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0：关注 1取消关注
     *
     * @param status 0：关注 1取消关注
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