package com.joyzone.platform.core.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DynamicDTO implements Serializable {

    /**
     * 发动态者id
     */
    private Long userId;

    /**
     * 动态内容
     */
    private String content;

    /**
     * 0：谁都可以看 1：我关注和关注我的人
     */
    private Integer kind;


    private List<String> picturlUrls;


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

    public List<String> getPicturlUrls() {
        return picturlUrls;
    }

    public void setPicturlUrls(List<String> picturlUrls) {
        this.picturlUrls = picturlUrls;
    }
}