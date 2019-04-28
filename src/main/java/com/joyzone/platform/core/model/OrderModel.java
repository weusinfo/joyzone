package com.joyzone.platform.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = OrderModel.TABLE_NAME)
@ApiModel("订单信息")
public class OrderModel extends BaseModel{

    protected static final String TABLE_NAME = "orders";

    @Id
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("组队主键ID")
    @Column(name = "team_id")
    private Long teamId;

    /**
     * 0：组队店家订单；1：体验券店家订单
     */
    @Column(name = "order_type")
    @ApiModelProperty("类型0：组队店家订单；1：体验券店家订单")
    private Integer orderType;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    @ApiModelProperty("订单编号")
    private String orderNo;

    /**
     * 店家服务类型种类
     */
    @ApiModelProperty("店家服务类型种类ID")
    @Column(name = "shop_type_id")
    private Long shopTypeId;

    /**
     * 店家ID
     */
    @Column(name = "shop_id")
    @ApiModelProperty("店家ID")
    private Long shopId;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private BigDecimal price;

    /**
     * 组队人数
     */
    @Column(name = "person_num")
    @ApiModelProperty("组队人数")
    private Integer personNum;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;



    /**
     * 玩耍时间
     */
    @Column(name = "pay_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("玩耍时间")
    private Date payTime;

    /**
     * 0 失效  1有效
     */
    @ApiModelProperty("0 失效  1有效")
    private Integer status;

    @Transient
    public Long userId;

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
     * 获取0：组队店家订单；1：体验券店家订单
     *
     * @return order_type - 0：组队店家订单；1：体验券店家订单
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 设置0：组队店家订单；1：体验券店家订单
     *
     * @param orderType 0：组队店家订单；1：体验券店家订单
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(Long shopTypeId) {
        this.shopTypeId = shopTypeId;
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
     * 获取价格
     *
     * @return price - 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取组队人数
     *
     * @return person_num - 组队人数
     */
    public Integer getPersonNum() {
        return personNum;
    }

    /**
     * 设置组队人数
     *
     * @param personNum 组队人数
     */
    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}