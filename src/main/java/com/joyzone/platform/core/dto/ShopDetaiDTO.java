package com.joyzone.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.util.List;

@Configuration
@ApiModel("前端商家聚会tab页面详情交互DTO202011")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ShopDetaiDTO extends BaseModel{

    public ShopDetaiDTO(){

    }

    @ApiModelProperty("商家id")
    private Long shopId;

    @ApiModelProperty("商家名称")
    private String shopName;

    @ApiModelProperty("商家图片")
    private List<String> shopPicList;

    @ApiModelProperty("商家名称")
    private String shopAddress;

    @ApiModelProperty("商家电话")
    private String shopPhone;

    @ApiModelProperty("商家价格")
    private BigDecimal price;

    @ApiModelProperty("访问商家的用户id")
    private Long userId;

    @ApiModelProperty("商家正在聚会列表")
    private List<ActivityDTO> shopTeamList;

    @ApiModelProperty("经度")
    private BigDecimal lng;

    @ApiModelProperty("纬度")
    private BigDecimal lat;

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

    public List<String> getShopPicList() {
        return shopPicList;
    }

    public void setShopPicList(List<String> shopPicList) {
        this.shopPicList = shopPicList;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ActivityDTO> getShopTeamList() {
        return shopTeamList;
    }

    public void setShopTeamList(List<ActivityDTO> shopTeamList) {
        this.shopTeamList = shopTeamList;
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
}
