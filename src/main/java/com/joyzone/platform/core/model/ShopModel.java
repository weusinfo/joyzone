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

@Table(name = ShopModel.TABLE_NAME)
@ApiModel("店家信息")
public class ShopModel extends BaseModel {

    protected static final String TABLE_NAME = "shop";

    @Id
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("名称")
    @Excel(name="店家名称")
    private String name;

    @ApiModelProperty("手机号码")
    @Excel(name="手机号码")
    private String phone;

    @ApiModelProperty("地址")
    @Excel(name="地址")
    private String address;

    @ApiModelProperty("描述")
    private String description;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    @Excel(name="价格")
    private BigDecimal price;

    /**
     * 店家封面照片
     */
    @Column(name = "cover_img")
    @ApiModelProperty("店家封面照片")
    @Excel(name="店家封面照片")
    private String coverImg;

    /**
     * 店家状态；0：签约；1：待审核；2：已禁入
     */
    @ApiModelProperty("店家状态；0：签约；1：待审核；2：已禁入")
    @Excel(name="店家封面照片",replace= {"签约_0","待审核_1","已禁入_2"})
    private Integer status;

    /**
     * 工商注册照片
     */
    @ApiModelProperty("工商注册照片")
    @Column(name = "reg_img")
    private String regImg;

    /**
     * 法人信息照片
     */
    @ApiModelProperty("法人信息照片")
    @Column(name = "legal_person_img")
    private String legalPersonImg;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @Excel(name="创建时间",exportFormat = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @Excel(name="更新时间",exportFormat = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;

    @ApiModelProperty("经度")
    private BigDecimal lng;

    @ApiModelProperty("纬度")
    private BigDecimal lat;

    @ApiModelProperty("所在省")
    private String province;

    @ApiModelProperty("所在市")
    private String city;

    @ApiModelProperty("所在区")
    private String area;

    @ApiModelProperty("所属店家种类ID")
    @Column(name = "shop_type_id")
    private Long shopTypeId;

    @ApiModelProperty("所属店家种类名称")
    @Column(name = "shop_type_name")
    @Excel(name="店家种类名称")
    private String shopTypeName;

    @Transient
    @ApiModelProperty("开始时间 yyyy-MM-dd")
    @DateTimeFormat(pattern = DateUtils.DATE_PATTERN)
    private Date startTime;

    @Transient
    @ApiModelProperty("结束时间 yyyy-MM-dd")
    @DateTimeFormat(pattern = DateUtils.DATE_PATTERN)
    private Date endTime;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * 获取店家封面照片
     *
     * @return cover_img - 店家封面照片
     */
    public String getCoverImg() {
        return coverImg;
    }

    /**
     * 设置店家封面照片
     *
     * @param coverImg 店家封面照片
     */
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }


    /**
     * 获取店家状态；1：签约；2：待审核；3：已禁入
     *
     * @return status - 店家状态；1：签约；2：待审核；3：已禁入
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置店家状态；1：签约；2：待审核；3：已禁入
     *
     * @param status 店家状态；1：签约；2：待审核；3：已禁入
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取工商注册照片
     *
     * @return reg_img - 工商注册照片
     */
    public String getRegImg() {
        return regImg;
    }

    /**
     * 设置工商注册照片
     *
     * @param regImg 工商注册照片
     */
    public void setRegImg(String regImg) {
        this.regImg = regImg;
    }

    /**
     * 获取法人信息照片
     *
     * @return legal_person_img - 法人信息照片
     */
    public String getLegalPersonImg() {
        return legalPersonImg;
    }

    /**
     * 设置法人信息照片
     *
     * @param legalPersonImg 法人信息照片
     */
    public void setLegalPersonImg(String legalPersonImg) {
        this.legalPersonImg = legalPersonImg;
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
}