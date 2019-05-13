package com.joyzone.platform.module.admin.controller;

import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.ShopModel;
import com.joyzone.platform.core.service.ShopService;
import com.joyzone.platform.core.service.ShopTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
