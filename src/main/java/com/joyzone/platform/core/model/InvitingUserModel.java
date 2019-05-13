package com.joyzone.platform.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = InvitingUserModel.TABLE_NAME)
@ApiModel("接受邀请函信息")
public class InvitingUserModel extends BaseModel{
    protected static final String TABLE_NAME = "inviting_user";

    @Id
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("发起邀请函主键")
    @Column(name = "inviting_id")
    private Long invitingId;

    @ApiModelProperty("用户ID")
    @Column(name = "user_id")
    private Long userId;

    /**
     * 0 退出 1 接受
     */
    @ApiModelProperty("0 退出 1 接受")
    private Integer status;

    /**
     * 0收到正式函 1未收到
     */
    @ApiModelProperty("0收到正式函 1未收到")
    private Integer confirm;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return inviting_id
     */
    public Long getInvitingId() {
        return invitingId;
    }

    /**
     * @param invitingId
     */
    public void setInvitingId(Long invitingId) {
        this.invitingId = invitingId;
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
     * 获取0 退出 1 接受
     *
     * @return status - 0 退出 1 接受
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 退出 1 接受
     *
     * @param status 0 退出 1 接受
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
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

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}