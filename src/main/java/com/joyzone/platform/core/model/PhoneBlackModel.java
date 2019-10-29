package com.joyzone.platform.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;

import java.util.Date;
import javax.persistence.*;

@Table(name = "phone_black")
public class PhoneBlackModel  extends BaseModel{
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 1 封号， 2禁入
     */
    private Integer type;

    /**
     * 封号或禁入的原因
     */
    private String reason;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    /**
     * 更新时间
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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取1 封号， 2禁入
     *
     * @return type - 1 封号， 2禁入
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1 封号， 2禁入
     *
     * @param type 1 封号， 2禁入
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取封号或禁入的原因
     *
     * @return reason - 封号或禁入的原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置封号或禁入的原因
     *
     * @param reason 封号或禁入的原因
     */
    public void setReason(String reason) {
        this.reason = reason;
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