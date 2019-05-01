package com.joyzone.platform.module.app.controller;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.ShopDto;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.service.ShopService;
import com.joyzone.platform.core.service.ShopTypeService;
import com.joyzone.platform.core.vo.AppShopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:TODO
 * @author Mr.Gx
 * date: 2019/4/29
 */
@RestController
@RequestMapping("/app_shop")
@Api(description = "AppShopController",tags = "app首页店家信息相关接口")
public class AppShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopTypeService shopTypeService;

    @PostMapping("getAppShopList")
    @ApiOperation("根据种类获取店家信息 @Mr.Gx")
    public R getAppShopList(ShopDto shopDto){
        if(shopDto == null)
            return R.error("参数不能为空");
        if(shopDto.getLng() == null)
            return R.error("用户所在的经度不能为空");
        if(shopDto.getLat() == null)
            return R.error("用户所在的纬度不能为空");
        if(shopDto.getPageNum() == null)
            shopDto.setPageNum(BaseModel.PAGE_NUM);
        if(shopDto.getPageSize() == null)
            shopDto.setPageSize(BaseModel.PAGE_SIZE);

        return R.ok(shopService.getAppShopList(shopDto));
    }

    @PostMapping("getAppShopTypeList")
    @ApiOperation("根据店家种类信息 @Mr.Gx")
    public R getAppShopTypeList(){
        return R.ok(shopTypeService.findByShopType());
    }
}
