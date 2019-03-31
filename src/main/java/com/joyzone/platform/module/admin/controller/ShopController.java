package com.joyzone.platform.module.admin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.ShopModel;
import com.joyzone.platform.core.service.ShopService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/shop")
@Api(value="后台门店管理V",description="后台门店管理",tags="1.0")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@PostMapping("/add")
	public R addShop(ShopModel shop) {
		shopService.addShop(shop);
		return R.ok("添加成功");
	}
	
	@PostMapping("/update")
	public R updateShop(ShopModel shop) {
		shopService.updateShop(shop);
		return R.ok("更新成功");
	}
	
	@PostMapping("/delete")
	public R batchDelete(@RequestParam("ids") Long[] ids) {
		shopService.batchDelete(ids);
		return R.ok("删除成功");
	}
	
	@PostMapping("/list")
	public R listShops(ShopModel shop) {
		List<ShopModel> shops = shopService.listShops(shop);
		if(PublicUtil.isEmpty(shops)) return R.error("没有门店数据");
		return R.ok(shops);
	}

}
