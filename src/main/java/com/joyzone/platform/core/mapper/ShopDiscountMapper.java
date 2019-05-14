package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ShopDiscountModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ShopDiscountMapper extends Mapper<ShopDiscountModel> {

    List<ShopDiscountModel> getShopDiscountList(ShopDiscountModel shopDiscountModel);

    int delShopDiscounts(@Param("ids") Long[] ids);
}