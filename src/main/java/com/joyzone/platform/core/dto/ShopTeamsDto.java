package com.joyzone.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zy on 2019/4/20.
 */
@ApiModel("店家组队列表信息DTO")
@Configuration
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ShopTeamsDto extends BaseModel {

    @ApiModelProperty("店家ID")
    private Long shopId;

    @ApiModelProperty("店家组队ID")
    private Long teamId;

    @ApiModelProperty("店家封面")
    private String shopCoverImg;

    @ApiModelProperty("店家名称")
    private String shopName;

    @ApiModelProperty("店家地址")
    private String shopAddress;

    @ApiModelProperty("店家价格")
    private BigDecimal shopPrice;

    @ApiModelProperty("店家该组队人员信息")
    private List<UserJoinTeamDto> teamUsers;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getShopCoverImg() {
        return shopCoverImg;
    }

    public void setShopCoverImg(String shopCoverImg) {
        this.shopCoverImg = shopCoverImg;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public List<UserJoinTeamDto> getTeamUsers() {
        return teamUsers;
    }

    public void setTeamUsers(List<UserJoinTeamDto> teamUsers) {
        this.teamUsers = teamUsers;
    }



}
