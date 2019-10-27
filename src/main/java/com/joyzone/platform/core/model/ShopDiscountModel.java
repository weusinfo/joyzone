package com.joyzone.platform.core.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = ShopDiscountModel.TABLE_NAME)
@ApiModel("折扣券信息")
public class ShopDiscountModel extends BaseModel{

    protected static final String TABLE_NAME = "shop_discount";

    @Id
    @Excel(name="折扣券ID")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 折扣券名称
     */
    @Excel(name="折扣券名称")
    @ApiModelProperty("折扣券名称")
    private String name;

    /**
     * 店家ID
     */
    @Column(name = "shop_id")
    @ApiModelProperty("店家ID")
    private Long shopId;

    /**
     * 店家名称
     */
    @Column(name = "shop_name")
    @Excel(name="组队店家名称")
    @ApiModelProperty("组队店家名称")
    private String shopName;

    /**
     * 店家种类ID
     */
    @Column(name = "shop_type_id")
    @ApiModelProperty("店家种类ID")
    private Long shopTypeId;

    /**
     * 店家种类名称
     */
    @Column(name = "shop_type_name")
    @Excel(name="组队店家种类")
    @ApiModelProperty("组队店家种类名称")
    private String shopTypeName;

    @Excel(name="折扣后的价格")
    @ApiModelProperty("折扣后的价格")
    private BigDecimal price;

    /**
     * 折扣  9折 = 0.9
     */
    @Excel(name="折扣数")
    @ApiModelProperty("折扣  9折 = 0.9")
    private Integer discount;

    /**
     * 人数限制
     */
    @Excel(name="允许人数")
    @ApiModelProperty("允许人数")
    private Integer number;

    /**
     * 状态：0 失效 1 正常
     */
    @Excel(name="状态",replace = {"失效_0","有效_1"})
    @ApiModelProperty("状态：0 失效 1 正常")
    private Integer status;

    /**
     * 玩耍时间
     */
    @Column(name = "play_time")
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    @Excel(name="体验时间",exportFormat = "yyyy-MM-dd HH:mm")
    @ApiModelProperty("玩耍时间")
    private Date playTime;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    @Excel(name="创建时间",exportFormat = "yyyy-MM-dd HH:mm")
    private Date createTime;

    @Column(name = "update_time")
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    private Date updateTime;

    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    @Transient
    @ApiModelProperty("根据开始时间查询信息")
    private Date startTime;

    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    @Transient
    @ApiModelProperty("根据结束时间查询信息")
    private Date endTime;

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
     * 获取店家ID
     *
     * @return shop_id - 店家ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置店家ID
     *
     * @param shopId 店家ID
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取店家名称
     *
     * @return shop_name - 店家名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置店家名称
     *
     * @param shopName 店家名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 获取店家种类ID
     *
     * @return shop_type_id - 店家种类ID
     */
    public Long getShopTypeId() {
        return shopTypeId;
    }

    /**
     * 设置店家种类ID
     *
     * @param shopTypeId 店家种类ID
     */
    public void setShopTypeId(Long shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    /**
     * 获取店家种类名称
     *
     * @return shop_type_name - 店家种类名称
     */
    public String getShopTypeName() {
        return shopTypeName;
    }

    /**
     * 设置店家种类名称
     *
     * @param shopTypeName 店家种类名称
     */
    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }

    /**
     * 获取折扣  9折 = 0.9 
     *
     * @return discount - 折扣  9折 = 0.9 
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * 设置折扣  9折 = 0.9 
     *
     * @param discount 折扣  9折 = 0.9 
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * 获取状态：0 失效 1 正常
     *
     * @return status - 状态：0 失效 1 正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0 失效 1 正常
     *
     * @param status 状态：0 失效 1 正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取玩耍时间
     *
     * @return play_time - 玩耍时间
     */
    public Date getPlayTime() {
        return playTime;
    }

    /**
     * 设置玩耍时间
     *
     * @param playTime 玩耍时间
     */
    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}