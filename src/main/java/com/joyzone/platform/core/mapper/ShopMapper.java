package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.base.BaseMapper;
import com.joyzone.platform.core.dto.ShopDto;
import com.joyzone.platform.core.dto.ShopHomeDto;
import com.joyzone.platform.core.dto.ShopInfoDto;
import com.joyzone.platform.core.model.ShopModel;
import com.joyzone.platform.core.vo.AppShopVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShopMapper extends BaseMapper<ShopModel> {
	
	int addShop(ShopModel shop);
	
	int updateShop(ShopModel shop);
	
	int batchDelete(Long[] ids);

	List<AppShopVO> getAppShopList(ShopDto shopDto);

	ShopModel findById(@Param("id") Long id);

	ShopInfoDto findShopInfoDtoByShopId(@Param("shopId") Long shopId,@Param("userId") Long userId);

	List<ShopModel> findAll();

	List<AppShopVO> getAppShopHomeList(@Param("shopTypeId") Long shopTypeId,@Param("size") Integer size);

	List<String> getShopPicList(@Param("size") Integer size);

	ShopHomeDto getShopHomeList(@Param("userId") Long userId);

	List<ShopModel> getAppShopByTypeId(@Param("typeId") Long typeId);

	List<Map<String,Object>> getShopMapList(@Param("name") String name,
											@Param("pageNum") Integer pageNum,
											@Param("pageSize")Integer pageSize);

	List<ShopModel> listShops(ShopModel shopModel);

	List<String> getShopCoverListByShopId(@Param("shopId") Long shopId);
}