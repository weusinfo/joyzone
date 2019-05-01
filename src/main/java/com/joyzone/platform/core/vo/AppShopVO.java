package com.joyzone.platform.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * Description:TODO
 * @author Mr.Gx
 * date: 2019/4/29
 */
@ApiModel("用户APP展示")
public class AppShopVO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("店家封面照片")
    private String coverImg;

    @ApiModelProperty("经度")
    private Double lng;

    @ApiModelProperty("纬度")
    private Double lat;

    @ApiModelProperty("离商家的距离")
    private Double distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
