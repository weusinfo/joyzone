package com.joyzone.platform.core.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = OrderModel.TABLE_NAME)
public class OrderModel extends BaseModel{

    protected static final String TABLE_NAME = "orders";

    @Id
    private Long id;

    @Column(name = "team_id")
    private Long teamId;

    /**
     * 0：组队店家订单；1：体验券店家订单
     */
    @Column(name = "order_type")
    private Integer orderType;

    /**
     * 店家服务类型；：真人cs  1：健身馆。。。
     */
    @Column(name = "shop_kind")
    private Integer shopKind;

    /**
     * 店家ID
     */
    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 组队人数
     */
    @Column(name = "person_num")
    private Integer personNum;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
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

    /**
     * 获取店家服务类型；：真人cs  1：健身馆。。。
     *
     * @return shop_kind - 店家服务类型；：真人cs  1：健身馆。。。
     */
    public Integer getShopKind() {
        return shopKind;
    }

    /**
     * 设置店家服务类型；：真人cs  1：健身馆。。。
     *
     * @param shopKind 店家服务类型；：真人cs  1：健身馆。。。
     */
    public void setShopKind(Integer shopKind) {
        this.shopKind = shopKind;
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
}