package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ShopModel;
import tk.mybatis.mapper.common.Mapper;

public interface ShopMapper extends Mapper<ShopModel> {
	
	int addShop(ShopModel shop);
	
	int updateShop(ShopModel shop);
}