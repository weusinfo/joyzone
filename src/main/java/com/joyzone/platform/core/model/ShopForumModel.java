package com.joyzone.platform.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "shop_forum")
public class ShopForumModel {
    /**
     * 主键id
     */
    @Id
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 店家id
     */
    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 0：环境很好 1：环境一般 2：环境很差
     */
    @Column(name = "environment_forum")
    private Integer environmentForum;

    /**
     * 0：服务很好 1：服务一般 2：服务很差
     */
    @Column(name = "service_forum")
    private Integer serviceForum;

    /**
     * 0：体验很棒 1：体验一般 2：体验很差
     */
    @Column(name = "experience_forum")
    private Integer experienceForum;

    /**
     * 评价内容
     */
    @Column(name = "forum_text")
    private String forumText;

    /**
     * 评价图片
     */
    private String pictures;

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
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取店家id
     *
     * @return shop_id - 店家id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置店家id
     *
     * @param shopId 店家id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取0：环境很好 1：环境一般 2：环境很差
     *
     * @return environment_forum - 0：环境很好 1：环境一般 2：环境很差
     */
    public Integer getEnvironmentForum() {
        return environmentForum;
    }

    /**
     * 设置0：环境很好 1：环境一般 2：环境很差
     *
     * @param environmentForum 0：环境很好 1：环境一般 2：环境很差
     */
    public void setEnvironmentForum(Integer environmentForum) {
        this.environmentForum = environmentForum;
    }

    /**
     * 获取0：服务很好 1：服务一般 2：服务很差
     *
     * @return service_forum - 0：服务很好 1：服务一般 2：服务很差
     */
    public Integer getServiceForum() {
        return serviceForum;
    }

    /**
     * 设置0：服务很好 1：服务一般 2：服务很差
     *
     * @param serviceForum 0：服务很好 1：服务一般 2：服务很差
     */
    public void setServiceForum(Integer serviceForum) {
        this.serviceForum = serviceForum;
    }

    /**
     * 获取0：体验很棒 1：体验一般 2：体验很差
     *
     * @return experience_forum - 0：体验很棒 1：体验一般 2：体验很差
     */
    public Integer getExperienceForum() {
        return experienceForum;
    }

    /**
     * 设置0：体验很棒 1：体验一般 2：体验很差
     *
     * @param experienceForum 0：体验很棒 1：体验一般 2：体验很差
     */
    public void setExperienceForum(Integer experienceForum) {
        this.experienceForum = experienceForum;
    }

    /**
     * 获取评价内容
     *
     * @return forum_text - 评价内容
     */
    public String getForumText() {
        return forumText;
    }

    /**
     * 设置评价内容
     *
     * @param forumText 评价内容
     */
    public void setForumText(String forumText) {
        this.forumText = forumText;
    }

    /**
     * 获取评价图片
     *
     * @return pictures - 评价图片
     */
    public String getPictures() {
        return pictures;
    }

    /**
     * 设置评价图片
     *
     * @param pictures 评价图片
     */
    public void setPictures(String pictures) {
        this.pictures = pictures;
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