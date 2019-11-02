package com.joyzone.platform.core.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.common.utils.DateUtils;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description:TODO
 * @author ZY
 * date: 2019/6/4
 */
@ApiModel("店家詳情頁相关信息")
@Configuration
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ShopInfoDto extends BaseModel {

    @ApiModelProperty("店家id")
    private Long shopId;

    @ApiModelProperty("店家名称")
    private String shopName;


    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("描述")
    private String description;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private BigDecimal price;

    /**
     * 店家封面照片
     */
    @ApiModelProperty("店家封面照片")
    private String coverImg;

    /**
     * 店家状态；0：签约；1：待审核；2：已禁入
     */
    @ApiModelProperty("店家状态；0：签约；1：待审核；2：已禁入")
    private Integer status;

    /**
     * 工商注册照片
     */
    @ApiModelProperty("工商注册照片")
    private String regImg;

    /**
     * 法人信息照片
     */
    @ApiModelProperty("法人信息照片")
    private String legalPersonImg;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
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
    private Long shopTypeId;

    @ApiModelProperty("所属店家种类名称")
    private String shopTypeName;

    @ApiModelProperty("店家是否被收藏 0：收藏 1未收藏")
    private Integer shopCollectStatus;

    @ApiModelProperty("商家有效正在组队信息集合")
    private List<ShopTeamsDto> shopTeamsDtoList;

    @ApiModelProperty("店家轮播图片")
    private List<String> coverImgList;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRegImg() {
        return regImg;
    }

    public void setRegImg(String regImg) {
        this.regImg = regImg;
    }

    public String getLegalPersonImg() {
        return legalPersonImg;
    }

    public void setLegalPersonImg(String legalPersonImg) {
        this.legalPersonImg = legalPersonImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getShopCollectStatus() {
        return shopCollectStatus;
    }

    public void setShopCollectStatus(Integer shopCollectStatus) {
        this.shopCollectStatus = shopCollectStatus;
    }

    public List<ShopTeamsDto> getShopTeamsDtoList() {
        return shopTeamsDtoList;
    }

    public void setShopTeamsDtoList(List<ShopTeamsDto> shopTeamsDtoList) {
        this.shopTeamsDtoList = shopTeamsDtoList;
    }

    public List<String> getCoverImgList() {
        return coverImgList;
    }

    public void setCoverImgList(List<String> coverImgList) {
        this.coverImgList = coverImgList;
    }
}
