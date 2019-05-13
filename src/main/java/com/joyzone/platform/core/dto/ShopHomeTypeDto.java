package com.joyzone.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zy on 2019/5/10.
 */
@ApiModel("商家首页店家类型DTO")
@Configuration
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ShopHomeTypeDto extends BaseModel {

    @ApiModelProperty("店家类型ID")
    private Long shopTypeId;

    @ApiModelProperty("店家类型名称")
    private String shopTypeName;

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
