package com.joyzone.platform.core.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.github.pagehelper.util.StringUtil;
import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.core.mapper.ShopMapper;
import com.joyzone.platform.core.model.ShopModel;

@Service
public class ShopService extends BaseService<ShopModel> {
	
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

}
