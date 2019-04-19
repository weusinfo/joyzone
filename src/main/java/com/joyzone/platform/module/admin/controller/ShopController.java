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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/shop")
@Api(tags="后台门店管理",description="后台门店管理")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@ApiOperation("添加商户")
	@PostMapping("/add")
	@ApiImplicitParams(value= {
			@ApiImplicitParam(name="name", value="商户名字", required=true,paramType="form"),
			@ApiImplicitParam(name="phone", value="商户联系电话", required=true,paramType="form"),
			@ApiImplicitParam(name="address", value="商户地址", required=true,paramType="form"),
			@ApiImplicitParam(name="description", value="商户描述", required=true, paramType="form"),
			@ApiImplicitParam(name="type", value="商户类型:0:组队店家;1:体验券店家", required=true, paramType="form"),
			@ApiImplicitParam(name="price", value="组队店家", paramType="form"),
			@ApiImplicitParam(name="priceTaste", value="体验价格", required=true, paramType="form"),
			@ApiImplicitParam(name="shopKind", value="店家提供的活动类型", required=true, paramType="form"),
			@ApiImplicitParam(name="regImg", value="工商注册照片", required=false),
			@ApiImplicitParam(name="legalPersonImg", value="法人信息照片", required=false)
	})
	public R addShop(ShopModel shop) {
		shopService.addShop(shop);
		return R.ok("添加成功");
	}
	
	@ApiOperation("更新商户")
	@PostMapping("/update")
	public R updateShop(ShopModel shop) {
		shopService.updateShop(shop);
		return R.ok("更新成功");
	}
	
	@ApiOperation("根据ID删除商户")
	@PostMapping("/delete")
	public R batchDelete(@RequestParam("ids") Long[] ids) {
		shopService.batchDelete(ids);
		return R.ok("删除成功");
	}
	
	@ApiOperation("获取商户列表")
	@PostMapping("/list")
	public R listShops(ShopModel shop) {
		List<ShopModel> shops = shopService.listShops(shop);
		if(PublicUtil.isEmpty(shops)) return R.error("没有门店数据");
		return R.ok(shops);
	}

}
