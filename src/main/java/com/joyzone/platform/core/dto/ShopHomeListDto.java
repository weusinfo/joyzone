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
@ApiModel("商家首页店家DTO")
@Configuration
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ShopHomeListDto extends BaseModel {

    @ApiModelProperty("店家种类ID")
    private Long shopTypeId;

    @ApiModelProperty("店家种类名称")
    private String shopTypeName;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("种类下店家集合")
    private List<ShopHomeListCoverDto> shopHomeCoverList;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<ShopHomeListCoverDto> getShopHomeCoverList() {
        return shopHomeCoverList;
    }

    public void setShopHomeCoverList(List<ShopHomeListCoverDto> shopHomeCoverList) {
        this.shopHomeCoverList = shopHomeCoverList;
    }
}
