package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ShopDiscountModel;
        import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ShopDiscountMapper extends Mapper<ShopDiscountModel> {

    public List<ShopDiscountModel> getShopDiscountList(ShopDiscountModel shopDiscountModel);
}