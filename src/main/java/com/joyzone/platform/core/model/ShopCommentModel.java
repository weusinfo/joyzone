package com.joyzone.platform.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "shop_comment")
public class ShopCommentModel {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 评论者id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 商家id
     */
    @Column(name = "shop_id")
    private Long shopId;

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

    private Byte type;

    @Column(name = "is_anonym")
    private Byte isAnonym;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论相片地址
     */
    @Column(name = "pic_urls")
    private String picUrls;

    /**
     * 商家回复
     */
    @Column(name = "admin_content")
    private String adminContent;

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
     * 获取评论者id
     *
     * @return user_id - 评论者id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置评论者id
     *
     * @param userId 评论者id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取商家id
     *
     * @return shop_id - 商家id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置商家id
     *
     * @param shopId 商家id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    /**
     * @return type
     */
    public Byte getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * @return is_anonym
     */
    public Byte getIsAnonym() {
        return isAnonym;
    }

    /**
     * @param isAnonym
     */
    public void setIsAnonym(Byte isAnonym) {
        this.isAnonym = isAnonym;
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取评论相片地址
     *
     * @return pic_urls - 评论相片地址
     */
    public String getPicUrls() {
        return picUrls;
    }

    /**
     * 设置评论相片地址
     *
     * @param picUrls 评论相片地址
     */
    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }

    /**
     * 获取商家回复
     *
     * @return admin_content - 商家回复
     */
    public String getAdminContent() {
        return adminContent;
    }

    /**
     * 设置商家回复
     *
     * @param adminContent 商家回复
     */
    public void setAdminContent(String adminContent) {
        this.adminContent = adminContent;
    }
}