package com.joyzone.platform.core.dto;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@ApiModel("订单信息DTO")
public class OrderDTO extends BaseModel {

    @ApiModelProperty("主键")
    private Long id;


    @Excel(name="订单编号")
    @ApiModelProperty("订单编号")
    private String orderNo;

    //0：组队店家订单；1：体验券店家订单
    @ApiModelProperty("订单类型：0 组队店家订单,1 体验券店家订单")
    @Excel(name="订单类型",replace = {"组队店家订单_0","体验券店家订单_1"})
    private Integer orderType;

    @ApiModelProperty("店家名称")
    @Excel(name="店家名称")
    private String shopName;

    @Excel(name="店家类型")
    @ApiModelProperty("店家类型名称")
    private String shopTypeName;

    @Excel(name="参加人数")
    @ApiModelProperty("参加人数")
    private Integer personNum;

    @Excel(name="订单价格")
    @ApiModelProperty("订单价格")
    private BigDecimal price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name="体验时间",exportFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("体验时间")
    private Date payTime;

    @ApiModelProperty("组队ID")
    private Long teamId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name="创建时间",exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("店家类型ID")
    private Long shopTypeId;

    @ApiModelProperty("店家ID")
    private Long shopId;

    @ApiModelProperty("订单状态：0 失效  1有效")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(Long shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    public String getShopTypeName() {
        return shopTypeName;
    }

    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
