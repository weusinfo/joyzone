package com.joyzone.platform.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = "forum_fabulous")
@ApiModel("是否点赞")
public class ForumFabulous {

    public static final int FORUM_TYPE_ZT = 1 ;//主贴
    public static final int FORUM_TYPE_GT = 2 ;//跟帖

    @Id
    private Long id;

    @Column(name = "user_id")
    @ApiModelProperty("用户ID")
    private Long userId;

    @Column(name = "forum_id")
    private Long forumId;

    @Column(name = "forum_detail_id")
    private Long forumDetailId;

    /**
     * 1 主贴   2 跟帖
     */
    private Integer type;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return forum_id
     */
    public Long getForumId() {
        return forumId;
    }

    /**
     * @param forumId
     */
    public void setForumId(Long forumId) {
        this.forumId = forumId;
    }

    /**
     * @return forum_detail_id
     */
    public Long getForumDetailId() {
        return forumDetailId;
    }

    /**
     * @param forumDetailId
     */
    public void setForumDetailId(Long forumDetailId) {
        this.forumDetailId = forumDetailId;
    }

    /**
     * 获取1 主贴   2 跟帖
     *
     * @return type - 1 主贴   2 跟帖
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1 主贴   2 跟帖
     *
     * @param type 1 主贴   2 跟帖
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}