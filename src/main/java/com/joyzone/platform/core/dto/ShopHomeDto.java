package com.joyzone.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by zy on 2019/5/10.
 */
@ApiModel("商家首页DTO")
@Configuration
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ShopHomeDto extends BaseModel {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户昵称")
    private String userName;

    @ApiModelProperty("商家首页banner图DTO集合")
    private List<ShopHomeBannerDTO> shopHomeBannerDTOList;

    @ApiModelProperty("商家首页店家类型DTO集合")
    private List<ShopHomeTypeDTO> shopHomeTypeDTOList;    //暂时按UI图上的那8个店家种类来，前端先写死

    @ApiModelProperty("商家首页热门店家DTO集合")
    private List<ShopHomeHotDTO> shopHomeHotDTOList;

    @ApiModelProperty("商家首页店家DTO集合")
    private List<ShopHomeListDto> shopHomeListDtoList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ShopHomeBannerDTO> getShopHomeBannerDTOList() {
        return shopHomeBannerDTOList;
    }

    public void setShopHomeBannerDTOList(List<ShopHomeBannerDTO> shopHomeBannerDTOList) {
        this.shopHomeBannerDTOList = shopHomeBannerDTOList;
    }

    public List<ShopHomeTypeDTO> getShopHomeTypeDtoList() {
        return shopHomeTypeDTOList;
    }

    public void setShopHomeTypeDtoList(List<ShopHomeTypeDTO> shopHomeTypeDTOList) {
        this.shopHomeTypeDTOList = shopHomeTypeDTOList;
    }

    public List<ShopHomeListDto> getShopHomeListDtoList() {
        return shopHomeListDtoList;
    }

    public void setShopHomeListDtoList(List<ShopHomeListDto> shopHomeListDtoList) {
        this.shopHomeListDtoList = shopHomeListDtoList;
    }

    public List<ShopHomeHotDTO> getShopHomeHotDTOList() {
        return shopHomeHotDTOList;
    }

    public void setShopHomeHotDTOList(List<ShopHomeHotDTO> shopHomeHotDTOList) {
        this.shopHomeHotDTOList = shopHomeHotDTOList;
    }
}
