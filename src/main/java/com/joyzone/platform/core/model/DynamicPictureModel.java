package com.joyzone.platform.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dynamic_picture")
public class DynamicPictureModel {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 动态id
     */
    @Column(name = "dynamic_id")
    private Long dynamicId;

    /**
     * 动态图片url
     */
    @Column(name = "picture_url")
    private String pictureUrl;

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
     * 获取动态图片url
     *
     * @return picture_url - 动态图片url
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * 设置动态图片url
     *
     * @param pictureUrl 动态图片url
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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