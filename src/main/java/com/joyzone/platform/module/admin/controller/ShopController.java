package com.joyzone.platform.module.admin.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.core.model.UserModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.ShopModel;
import com.joyzone.platform.core.service.ShopService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/shop")
@Api(tags="后台门店管理",description="ShopController")
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
			@ApiImplicitParam(name="price", value="组队店家", paramType="form"),
			@ApiImplicitParam(name="regImg", value="工商注册照片", required=false, paramType="form"),
			@ApiImplicitParam(name="legalPersonImg", value="法人信息照片", required=false, paramType="form")
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
		if(shops != null && shops.size() > 0){
			Page page = new Page();
			page = (Page)shops;
			return R.pageToData(page.getTotal(),page.getResult());
		}
		return R.pageToData(0L,shops);
	}


	@PostMapping("saveLngOrLat")
	@ApiOperation("测试使用（添加门店经纬度到缓存中取）")
	public R saveLngOrLat(ShopModel shop){
		if(shop != null && shop.getLng() != null){
			shopService.saveLngOrLat(shop);
		}else{
			List<ShopModel> list = shopService.findAll();
			if(list != null && list.size() > 0){
				for(ShopModel shopModel : list){
					shopService.saveLngOrLat(shopModel);
				}
			}
		}
		return R.ok("保存成功");
	}

	/**
	 * 用于新增系统用户时选择商家信息
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("getShopMapList")
	@ApiOperation("用于新增系统用户时选择商家信息")
	@ApiImplicitParams(value= {
			@ApiImplicitParam(name="name", value="商户名字", paramType="query"),
			@ApiImplicitParam(name="pageNum", value="页数", paramType="query",defaultValue = "1"),
			@ApiImplicitParam(name="pageSize", value="每页条数", paramType="query",defaultValue = "10"),
	})
	public R getShopMapList(String name,Integer pageNum,Integer pageSize){
		List<Map<String,Object>> list = shopService.getShopMapList(name,pageNum,pageSize);
		if(list != null && list.size() > 0){
			Page page = new Page();
			page = (Page)list;
			return R.pageToData(page.getTotal(),page.getResult());
		}
		return R.pageToData(0L,list);
	}

	@GetMapping("/exportShopXls")
	@ApiOperation("店家清单导出  @Mr.Gx")
	public void exportShopXls(ShopModel  shopModel, HttpServletResponse response) throws Exception{
		response.setHeader("content-Type", "application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode(Constants.SYS_SHOP, "UTF-8") + ".xls");
		response.setCharacterEncoding("UTF-8");
		List<ShopModel> list = shopService.getExportShopXls(shopModel);;
		ExportParams params = new ExportParams(Constants.SYS_SHOP, Constants.SYS_SHOP);
		Workbook workbook = ExcelExportUtil.exportExcel(params, ShopModel.class, list);
		workbook.write(response.getOutputStream());
	}

}
