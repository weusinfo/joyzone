package com.joyzone.platform.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = FavoriteModel.TABLE_NAME)
@ApiModel("收藏相关信息")
public class FavoriteModel extends BaseModel{

    protected static final String TABLE_NAME = "favorite";

    @Id
    @ApiModelProperty("主键")
    private Long id;

    @Column(name = "user_id")
    @ApiModelProperty("收藏人ID")
    private Long userId;

    @Column(name = "shop_id")
    @ApiModelProperty("收藏店家ID")
    private Long shopId;

    @ApiModelProperty("")
    private Integer status;

    /**
     * 收藏时间
     */
    @ApiModelProperty("收藏时间")
    @Column(name = "create_time")
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    @ApiModelProperty("修改时间")
    private Date updateTime;

    /**
     * 目标用户ID
     */
    @Column(name = "target_id")
    @ApiModelProperty("目标用户ID")
    private Long targetId;

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
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取收藏时间
     *
     * @return create_time - 收藏时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置收藏时间
     *
     * @param createTime 收藏时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}