package com.joyzone.platform.core.vo;

import com.joyzone.platform.core.model.ShopTypeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Description:TODO
 * @author Mr.Gx
 * date: 2019/5/4
 */
@ApiModel("商家首页信息")
public class AppShopHomeVO {

    @ApiModelProperty("商家首页轮播图")
    List<String> shopPicList;

    @ApiModelProperty("商家首页种类信息")
    List<ShopTypeModel> shopTypeModels;

    @ApiModelProperty("商家首页第一组商家信息")
    List<AppShopVO> appShops1;

    @ApiModelProperty("商家首页第二组商家信息")
    List<AppShopVO> appShops2;

    @ApiModelProperty("商家首页第三组商家信息")
    List<AppShopVO> appShops3;

    public List<AppShopVO> getAppShops1() {
        return appShops1;
    }

    public void setAppShops1(List<AppShopVO> appShops1) {
        this.appShops1 = appShops1;
    }

    public List<AppShopVO> getAppShops2() {
        return appShops2;
    }

    public void setAppShops2(List<AppShopVO> appShops2) {
        this.appShops2 = appShops2;
    }

    public List<AppShopVO> getAppShops3() {
        return appShops3;
    }

    public void setAppShops3(List<AppShopVO> appShops3) {
        this.appShops3 = appShops3;
    }

    public List<String> getShopPicList() {
        return shopPicList;
    }

    public void setShopPicList(List<String> shopPicList) {
        this.shopPicList = shopPicList;
    }

    public List<ShopTypeModel> getShopTypeModels() {
        return shopTypeModels;
    }

    public void setShopTypeModels(List<ShopTypeModel> shopTypeModels) {
        this.shopTypeModels = shopTypeModels;
    }
}
