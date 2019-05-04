package com.joyzone.platform.core.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.*;
import com.joyzone.platform.core.dto.ShopDto;
import com.joyzone.platform.core.vo.AppShopVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.github.pagehelper.util.StringUtil;
import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.ShopMapper;
import com.joyzone.platform.core.model.ShopModel;

import cn.hutool.core.util.NumberUtil;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class ShopService extends BaseService<ShopModel> {
	
	private Logger logger = LoggerFactory.getLogger(ShopService.class);

	@Value("${distance_range}")
	private double radius;

	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private RedisService redisService;

	
	public void validateShop(ShopModel shop) {
		if(StringUtils.isEmpty(shop.getName())) throw new JZException("门店名不能为空");
		if(StringUtils.isEmpty(shop.getAddress())) throw new JZException("门店地址不能为空");
		if(StringUtils.isEmpty(shop.getPhone())) throw new JZException("门店电话不能为空");
		if(StringUtils.isEmpty(shop.getCoverImg())) throw new JZException("请上传店家照片");
		if(StringUtil.isEmpty(shop.getDescription())) throw new JZException("请填写店家描述");
		if(StringUtil.isEmpty(shop.getProvince())) throw new JZException("请选择店家所在的省份");
		if(StringUtil.isEmpty(shop.getCity())) throw new JZException("请选择店家所在的市");
		if(StringUtil.isEmpty(shop.getArea())) throw new JZException("请选择店家所在的地区");
		if(shop.getLng() == null) throw new JZException("请填写店家所在经度");
		if(shop.getLat() == null) throw new JZException("请填写店家所在经度");
	}
	
	public void addShop(ShopModel shop) {
		validateShop(shop);
		shop.setStatus(ShopModel.STATUS_SUCCESS); //默认签约
		shop.setCreateTime(new Date());
		shopMapper.addShop(shop);
		Long shopId = shop.getId();
		if(shopId != null){
			Point point = new Point(shop.getLng().doubleValue(),shop.getLat().doubleValue());
			RedisGeoUtil.geoadd(redisService.getStringRedisTemplate(),RedisColumn.SHOP_LOCATION ,point,shopId.toString());
		}
	}
	
	public void updateShop(ShopModel shop) {
		validateShop(shop);
		shop.setUpdateTime(new Date());
		shopMapper.updateShop(shop);
	}
	
	public void batchDelete(Long[] ids) {
		int i = shopMapper.batchDelete(ids);
		if(i == 0) throw new JZException("删除门店失败");
	}
	
	public List<ShopModel> listShops(ShopModel shop){
		return shopMapper.select(shop);
	}
	
	public boolean exists(Long shopId) {
		Example example = new Example(ShopModel.class);
		example.createCriteria().andEqualTo("id", shopId);
		example.createCriteria().andEqualTo("status", 0);
		int i = selectCountByExample(example);
		if(i == 1) return true;
		return false;
	}
	/**
	 * 获取用户附近的商家信息
	 * @param shopDto
	 * @Mr.Gx
	 */
	public R getAppShopList(ShopDto shopDto){
		Double longitude = shopDto.getLng().doubleValue();
		Double latitude = shopDto.getLat().doubleValue();
		Point geoCoordinate=new Point(longitude,latitude);
		List<GeoRadiusDto>  geoRadiusResponseList = RedisGeoUtil.geoRadius(redisService.getStringRedisTemplate(), RedisColumn.SHOP_LOCATION,geoCoordinate,radius, Metrics.KILOMETERS, Sort.Direction.DESC);
		String areaCode = null;
		if(geoRadiusResponseList !=null && geoRadiusResponseList.size()>0){
			for (GeoRadiusDto geoRadiusResponse:geoRadiusResponseList) {
				String shopId = new String(geoRadiusResponse.getMember());
				ShopModel shop = shopMapper.findById(Long.valueOf(shopId));
				if(shop != null && (shop.getArea()!=null && !shop.getArea().equals("999999"))){
					areaCode = shop.getArea();
					break;
				}
			}
			if(areaCode==null) {
				areaCode= "999999";
			}
		}else{
			areaCode= "999999";
		}
		if(!"999999".equals(areaCode)){
			shopDto.setAreaCode(areaCode);
		}
		shopDto.setStatus(ShopModel.STATUS_SUCCESS); //已签约
		List<AppShopVO> list = shopMapper.getAppShopList(shopDto);
		//计算离商家的距离
		if(list!=null && list.size()>0){
			for (AppShopVO appShopVO: list) {
				Double lon = appShopVO.getLng() != null ? appShopVO.getLng() : 0.0;
				Double lat = appShopVO.getLat() != null ? appShopVO.getLat() : 0.0;
				appShopVO.setDistance(LocationUtils.getDistance(latitude,longitude,lat,lon));
			}
			Page page = new Page();
			page = (Page)list;
			return R.pageToData(page.getTotal(),page.getResult());
		}
		return R.pageToData(0L,list);
	}

}
