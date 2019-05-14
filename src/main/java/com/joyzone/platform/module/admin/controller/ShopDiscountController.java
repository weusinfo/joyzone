package com.joyzone.platform.module.admin.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.model.ShopCouponModel;
import com.joyzone.platform.core.model.ShopDiscountModel;
import com.joyzone.platform.core.service.ShopDiscountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shopDiscount")
@Api(tags="后台店家折扣券管理",description="ShopDiscountController")
public class ShopDiscountController {

    @Autowired
    private ShopDiscountService shopDiscountService;

    @ApiOperation("添加折扣券")
    @PostMapping("/saveShopDiscount")
    public R saveShopDiscount(ShopDiscountModel shopDiscountModel){
        if(shopDiscountModel == null)
            return R.error(R.STATUS_FAIL,"参数不能为空.");
        if(shopDiscountModel.getShopId() == null)
            return R.error(R.STATUS_FAIL,"店家ID不能为空.");
        if(StringUtils.isBlank(shopDiscountModel.getShopName()))
            return R.error(R.STATUS_FAIL,"店家名称不能为空.");
        if(shopDiscountModel.getShopTypeId() == null)
            return R.error(R.STATUS_FAIL,"店家种类ID不能为空.");
        if(StringUtils.isBlank(shopDiscountModel.getShopTypeName()))
            return R.error(R.STATUS_FAIL,"店家种类名称不能为空.");
        if(shopDiscountModel.getDiscount() == null)
            return R.error(R.STATUS_FAIL,"店家折扣系数不能为空.");
        if(shopDiscountModel.getPlayTime() == null)
            return R.error(R.STATUS_FAIL,"玩耍时间不能为空.");
        if(shopDiscountModel.getNumber() == null)
            return R.error(R.STATUS_FAIL,"人数限制不能为空.");
        if(shopDiscountModel.getNumber().intValue() < 1)
            return R.error(R.STATUS_FAIL,"人数限制不能低于1个.");

        Date date = new Date();
        shopDiscountModel.setCreateTime(date);
        shopDiscountModel.setUpdateTime(date);
        shopDiscountModel.setStatus(BaseModel.STATUS_SUCCESS);
        return shopDiscountService.save(shopDiscountModel) > 0 ?
                R.ok("添加成功"): R.error(R.STATUS_FAIL,"添加失败");
    }

    @GetMapping("getShopDiscountList")
    @ApiOperation("折扣券清单")
    public R getShopDiscountList(ShopDiscountModel shopDiscountModel){
        if(shopDiscountModel.getPageNum() == null)
            shopDiscountModel.setPageNum(BaseModel.PAGE_NUM);
        if(shopDiscountModel.getPageSize() == null)
            shopDiscountModel.setPageSize(BaseModel.PAGE_SIZE);

        List<ShopDiscountModel> list = shopDiscountService.getShopDiscountList(shopDiscountModel);
        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    @GetMapping("/exportShopDiscountXls")
    @ApiOperation("折扣券清单导出")
    public void exportShopDiscountXls(ShopDiscountModel shopDiscountModel, HttpServletResponse response) throws Exception{
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(Constants.SHOP_DISCOUNT, "UTF-8") + ".xls");
        response.setCharacterEncoding("UTF-8");
        List<ShopDiscountModel> list = shopDiscountService.getShopDiscountList(shopDiscountModel);;
        ExportParams params = new ExportParams(Constants.SHOP_DISCOUNT, Constants.SHOP_DISCOUNT);
        Workbook workbook = ExcelExportUtil.exportExcel(params, ShopDiscountModel.class, list);
        workbook.write(response.getOutputStream());
    }

    @PostMapping("delShopDiscounts")
    @ApiOperation("批量删除 @Mr.Gx")
    public R delShopDiscounts(@RequestParam("ids") Long[] ids){
        return shopDiscountService.delShopDiscounts(ids) > 0 ?
                R.ok() : R.error("批量删除失败");
    }

}
