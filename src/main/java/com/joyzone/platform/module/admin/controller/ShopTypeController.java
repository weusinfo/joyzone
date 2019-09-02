package com.joyzone.platform.module.admin.controller;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.ShopTypeModel;
import com.joyzone.platform.core.service.ShopTypeService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shopType")
@Api(tags="后台门店类型及种类",description="ShopTypeController")
public class ShopTypeController {
	
	@Autowired
	private ShopTypeService shopTypeService;
	
	@GetMapping("getShopTypeList")
	@ApiOperation("店家的类型及类型旗下的种：1 组队店家 2 体验店家")
	public R getShopTypeList(@RequestParam("type") Integer type) {
		return R.ok(shopTypeService.findByShopType(type));
	}
	
	@PostMapping("/addShopType")
	@ApiOperation("添加店家类型")
	public R addShopType(ShopTypeModel typeModel) {
		String errorMsg = shopTypeService.addShopType(typeModel);
		if(StringUtils.isEmpty(errorMsg)) {
			return R.ok();
		}
		return R.error(errorMsg);
	}
	
	/**
	 * 初始化部落群
	 * @return
	 */
	@PostMapping("/initTribes")
	public R initTribes() {
		shopTypeService.initTribles();
		return R.ok("群部落初始化完毕");
	}

}
