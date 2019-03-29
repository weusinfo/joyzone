package com.joyzone.platform.core.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.github.pagehelper.util.StringUtil;
import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.ShopMapper;
import com.joyzone.platform.core.model.ShopModel;
import cn.hutool.core.util.ObjectUtil;

@Service
public class ShopService extends BaseService<ShopModel> {
	
	private Logger logger = LoggerFactory.getLogger(ShopService.class);
	
	@Autowired
	private ShopMapper shopMapper;
	
	public void validateShop(ShopModel shop) {
		if(StringUtils.isEmpty(shop.getName())) throw new JZException("门店名不能为空");
		if(StringUtils.isEmpty(shop.getAddress())) throw new JZException("门店地址不能为空");
		if(StringUtils.isEmpty(shop.getPhone())) throw new JZException("门店电话不能为空");
		if(StringUtils.isEmpty(shop.getCoverImg())) throw new JZException("请上传店家照片");
		if(null == shop.getShopKind()) throw new JZException("请选择店家的活动类型"); 
		if(StringUtil.isEmpty(shop.getDescription())) throw new JZException("请填写店家描述");
	}
	
	public void addShop(ShopModel shop) {
		validateShop(shop);
		shop.setCreateTime(new Date());
		shopMapper.addShop(shop);
	}
	
	public void updateShop(ShopModel shop) {
		validateShop(shop);
		shop.setUpdateTime(new Date());
		shopMapper.updateShop(shop);
	}
	
	public void deleteShop(Long id) {
		if(ObjectUtil.isNull(id)) throw new JZException("门店编号不能为空");
		int i = shopMapper.deleteByPrimaryKey(id);
		if(i != 1) {
			logger.warn("==SHOP==DELETE==删除门店个数为"+i);
			throw new JZException("删除门店出现异常");
		}
	}
	
	public List<ShopModel> listShops(ShopModel shop){
		return shopMapper.select(shop);
	}

}
