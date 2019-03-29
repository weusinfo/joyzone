package com.joyzone.platform.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = TeamUsersModel.TABLE_NAME)
public class TeamUsersModel extends BaseModel{

    protected static final String TABLE_NAME = "team_users";

    @Id
    private Long id;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 入队状态：0：加入；1：退出
     */
    private Integer status;

    /**
     * 加入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间.退出时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * @return team_id
     */
    public Long getTeamId() {
        return teamId;
    }

    /**
     * @param teamId
     */
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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
     * 获取入队状态：0：加入；1：退出
     *
     * @return status - 入队状态：0：加入；1：退出
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置入队状态：0：加入；1：退出
     *
     * @param status 入队状态：0：加入；1：退出
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取加入时间
     *
     * @return create_time - 加入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置加入时间
     *
     * @param createTime 加入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间.退出时间
     *
     * @return update_time - 修改时间.退出时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间.退出时间
     *
     * @param updateTime 修改时间.退出时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}