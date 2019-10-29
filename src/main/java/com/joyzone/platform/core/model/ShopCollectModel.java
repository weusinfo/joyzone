package com.joyzone.platform.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;

import java.util.Date;
import javax.persistence.*;

@Table(name = "shop_collect")
@ApiModel("店家收藏表")
public class ShopCollectModel  extends BaseModel{
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 店家ID
     */
    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 0：收藏 1：取消收藏
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
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
     * 获取用户
     *
     * @return user_id - 用户
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户
     *
     * @param userId 用户
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取店家ID
     *
     * @return shop_id - 店家ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置店家ID
     *
     * @param shopId 店家ID
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取0：收藏 1：取消收藏
     *
     * @return status - 0：收藏 1：取消收藏
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0：收藏 1：取消收藏
     *
     * @param status 0：收藏 1：取消收藏
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
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}