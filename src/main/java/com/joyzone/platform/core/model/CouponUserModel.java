package com.joyzone.platform.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "coupon_user")
public class CouponUserModel  extends BaseModel{
    /**
     * 主键ID
     */
    @Id
    private Long id;

    /**
     * 体验券表ID
     */
    @Column(name = "coupon_id")
    private Long couponId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 领券状态：0：领取；1：退出
     */
    private Integer status;

    /**
     * 领券时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取体验券表ID
     *
     * @return coupon_id - 体验券表ID
     */
    public Long getCouponId() {
        return couponId;
    }

    /**
     * 设置体验券表ID
     *
     * @param couponId 体验券表ID
     */
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取领券状态：0：领取；1：退出
     *
     * @return status - 领券状态：0：领取；1：退出
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置领券状态：0：领取；1：退出
     *
     * @param status 领券状态：0：领取；1：退出
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取领券时间
     *
     * @return create_time - 领券时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置领券时间
     *
     * @param createTime 领券时间
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