package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ShopCollectModel;
import com.joyzone.platform.core.model.ShopModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ShopCollectMapper extends Mapper<ShopCollectModel> {

    List<ShopModel> getMyShopCollectList(ShopCollectModel model, @Param("userId") Long userId);


}