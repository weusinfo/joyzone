package com.joyzone.platform.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dynamic_comment")
public class DynamicCommentModel {
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
     * 动态id
     */
    @Column(name = "dynamic_id")
    private Long dynamicId;

    /**
     * 父级评论id
     */
    private Long pid;

    /**
     * 评论内容
     */
    private String content;

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
     * 获取父级评论id
     *
     * @return pid - 父级评论id
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父级评论id
     *
     * @param pid 父级评论id
     */
    public void setPid(Long pid) {
        this.pid = pid;
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