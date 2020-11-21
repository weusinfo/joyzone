package com.joyzone.platform.core.service;

import java.util.*;
import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.*;
import com.joyzone.platform.core.dto.ShopDTO;
import com.joyzone.platform.core.dto.ShopHomeDTO;
import com.joyzone.platform.core.dto.ShopInfoDTO;
import com.joyzone.platform.core.model.DocumentModel;
import com.joyzone.platform.core.vo.AppShopVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.github.pagehelper.util.StringUtil;
import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.ShopMapper;
import com.joyzone.platform.core.model.ShopModel;

import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
@Transactional
public class ShopService extends BaseService<ShopModel> {
	
	private Logger logger = LoggerFactory.getLogger(ShopService.class);

	@Value("${distance_range}")
	private double radius;//设置距离的范围

	@Autowired
	private RedisService redisService;

	@Autowired
	private ShopTypeService shopTypeService;

	@Autowired
	private DocumentService documentService;

	@Resource
	private ShopMapper shopMapper;

	
	public void validateShop(ShopModel shop) {
		if(shop.getShopTypeId() == null) throw new JZException("门店种类不能为空");
		if(StringUtils.isEmpty(shop.getName())) throw new JZException("门店名不能为空");
		if(StringUtils.isEmpty(shop.getAddress())) throw new JZException("门店地址不能为空");
		if(StringUtils.isEmpty(shop.getPhone())) throw new JZException("门店电话不能为空");
		if(StringUtils.isEmpty(shop.getCoverImg())) throw new JZException("请上传店家照片");
		if(StringUtil.isEmpty(shop.getDescription())) throw new JZException("请填写店家描述");
		if(StringUtil.isEmpty(shop.getProvince())) throw new JZException("请选择店家所在的省份");
		if(StringUtil.isEmpty(shop.getCity())) throw new JZException("请选择店家所在的市");
		if(StringUtil.isEmpty(shop.getArea())) throw new JZException("请选择店家所在的地区");
		if(shop.getPrice() == null) throw new JZException("店家所组队价格不能为空");
		if(shop.getLng() == null) throw new JZException("请填写店家所在经度");
		if(shop.getLat() == null) throw new JZException("请填写店家所在经度");
	}
	
	public void addShop(ShopModel shop) {
		validateShop(shop);
		String[] coverImgs = shop.getCoverImg().split(",");
		shop.setStatus(ShopModel.STATUS_SUCCESS); //默认签约
		shop.setCreateTime(new Date());
		shop.setCoverImg(coverImgs[0]); //
		shopMapper.addShop(shop);
		Long shopId = shop.getId();
		if(shopId != null){
			if(coverImgs.length > 0){
				for(String path : coverImgs){
					DocumentModel model = new DocumentModel();
					model.setFilePath(path);
					model.setFileName("shopImg");
					model.setOwner(shopId);
					model.setType(false);
					Date date = new Date();
					model.setCreateTime(date);
					model.setUpdateTime(date);
					documentService.save(model);
				}
			}

			//缓存中保存商家经纬度信息
			//		saveLngOrLat(shop);
		}
	}
	
	public void updateShop(ShopModel shop) {
		validateShop(shop);
		shop.setUpdateTime(new Date());
		int ret = shopMapper.updateShop(shop);
//		if(ret > 0){
//			//缓存中更新商家经纬度信息
//			saveLngOrLat(shop);
//		}
	}

	/**
	 * 缓存中保存或者更新商家经纬度信息
	 * @param shop
	 */
	public void saveLngOrLat(ShopModel shop){
		Point point = new Point(shop.getLng().doubleValue(),shop.getLat().doubleValue());
		RedisGeoUtil.geoadd(redisService.getStringRedisTemplate(),RedisColumn.SHOP_TEAM_LOCATION ,point,shop.getId().toString());
	}

	public List<ShopModel> findAll(){
		return shopMapper.findAll();
	}
	
	public void batchDelete(Long[] ids) {
		int i = shopMapper.batchDelete(ids);
		if(i == 0) throw new JZException("删除门店失败");
	}
	
	public List<ShopModel> listShops(ShopModel shop){
		return shopMapper.listShops(shop);
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
	 * 根据商家ID获取详情
	 * @param id
	 * @return
	 */
	public ShopModel findById(Long id){
		return shopMapper.findById(id);
	}

	public ShopInfoDTO findShopInfoDtoByShopId(Long shopId, Long userId){
		return shopMapper.findShopInfoDtoByShopId(shopId,userId);
	}

	/**
	 * 获取用户附近的商家信息
	 * @param shopDTO
	 * @Mr.Gx
	 */
	public R getAppShopList(ShopDTO shopDTO){
		shopDTO.setStatus(ShopModel.STATUS_SUCCESS); //已签约
		List<AppShopVO> list = shopMapper.getAppShopList(shopDTO);
		if(list!=null && list.size()>0){
			Page page = new Page();
			page = (Page)list;
			return R.pageToData(page.getTotal(),page.getResult());
		}
		return R.pageToData(0L,list);
	}

	public ShopHomeDTO getShopHomeList(Long userId){
		return shopMapper.getShopHomeList(userId);
	}

	public List<ShopModel> getAppShopByTypeId(Long typeId){
		return shopMapper.getAppShopByTypeId(typeId);
	}


	/**
	 * 获取商家的主键与名称给用户选择（待搜索）
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> getShopMapList(String name,Integer pageNum,Integer pageSize){
		return shopMapper.getShopMapList(name, pageNum, pageSize);
	}

	public List<ShopModel> getExportShopXls(ShopModel shopModel){
		return shopMapper.listShops(shopModel);
	}
}
