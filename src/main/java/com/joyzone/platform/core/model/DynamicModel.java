package com.joyzone.platform.core.model;

import com.alibaba.fastjson.JSONObject;
import com.joyzone.platform.common.utils.SensitiveWordUtils;
import com.joyzone.platform.core.dto.DynamicDTO;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;

@Table(name = "dynamic")
public class DynamicModel {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 发动态者id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 动态内容
     */
    private String content;

    /**
     * 0：谁都可以看 1：我关注和关注我的人
     */
    private Integer kind;

    /**
     * 点赞数
     */
    private Integer thumbs;

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

    private String pics;

    @Transient
    private String dynamicPics;

    public DynamicModel() { }

    public DynamicModel(DynamicDTO dynamicDTO) {
        this.userId = dynamicDTO.getUserId();
        // 获取实例
        SensitiveWordUtils wordUtils = SensitiveWordUtils.getInstance();
        this.content =  wordUtils.filterInfo(dynamicDTO.getContent());
        this.kind = dynamicDTO.getKind();
        this.thumbs = 0;
        Date date = new Date();
        this.createTime = date;
        this.updateTime = date;
        this.pics = null != dynamicDTO.getPicturlUrls() ? JSONObject.toJSONString(dynamicDTO.getPicturlUrls()) : new ArrayList().toString();
    }

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
     * 获取发动态者id
     *
     * @return user_id - 发动态者id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置发动态者id
     *
     * @param userId 发动态者id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取动态内容
     *
     * @return content - 动态内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置动态内容
     *
     * @param content 动态内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取0：谁都可以看 1：我关注和关注我的人
     *
     * @return kind - 0：谁都可以看 1：我关注和关注我的人
     */
    public Integer getKind() {
        return kind;
    }

    /**
     * 设置0：谁都可以看 1：我关注和关注我的人
     *
     * @param kind 0：谁都可以看 1：我关注和关注我的人
     */
    public void setKind(Integer kind) {
        this.kind = kind;
    }

    /**
     * 获取点赞数
     *
     * @return thumbs - 点赞数
     */
    public Integer getThumbs() {
        return thumbs;
    }

    /**
     * 设置点赞数
     *
     * @param thumbs 点赞数
     */
    public void setThumbs(Integer thumbs) {
        this.thumbs = thumbs;
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

    public String getDynamicPics() {
        return dynamicPics;
    }

    public void setDynamicPics(String dynamicPics) {
        this.dynamicPics = dynamicPics;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }
}