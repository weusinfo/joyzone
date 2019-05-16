package com.joyzone.platform.module.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.ShopDto;
import com.joyzone.platform.core.dto.ShopHomeDto;
import com.joyzone.platform.core.dto.ShopTeamsDto;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.model.ShopModel;
import com.joyzone.platform.core.model.ShopTypeModel;
import com.joyzone.platform.core.service.ShopService;
import com.joyzone.platform.core.service.ShopTypeService;
import com.joyzone.platform.core.service.TeamService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private TeamService teamService;

    @PostMapping("getAppShopHomeList")
    @ApiOperation("商家首页信息展示 @Mr.Gx")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "form"),
            @ApiImplicitParam(name = "pageSize",value = "三组商家信息显示数量,默认6条",paramType = "form",defaultValue = "6")
    })
    public R getAppShopHomeList(@RequestParam("userId") Long userId, @RequestParam("pageSize") Integer pageSize){
        return R.ok(shopService.getAppShopHomeList(userId,pageSize));
    }

    @PostMapping("getShopHomeList")
    @ApiOperation("商家首页信息展示 @zy")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "form")
    })
    public R getShopHomeList(@RequestParam("userId") Long userId){
        PageHelper.startPage(0,10);
        ShopHomeDto shopHome = shopService.getShopHomeList(userId);
        if(shopHome != null){
            return R.ok(shopHome);
        }
        return R.error("未获取到数据！");
    }

    @PostMapping("findByShopId")
    @ApiOperation("查看商家首页信息 @Mr.Gx")
    @ApiImplicitParam(name="id", value="商户id",paramType ="form")
    public R findByShopId(Long id){
        return R.ok(shopService.findById(id));
    }

    @PostMapping("getAppShopList")
    @ApiOperation("根据种类ID获取附近店家信息 @Mr.Gx")
    public R getAppShopList(ShopDto shopDto){
        if(shopDto == null)
            return R.error("参数不能为空");
        if(shopDto.getShopTypeId() == null)
            return R.error("种类ID不能为空");
        if(shopDto.getPageNum() == null)
            shopDto.setPageNum(BaseModel.PAGE_NUM);
        if(shopDto.getPageSize() == null)
            shopDto.setPageSize(BaseModel.PAGE_SIZE);

        return shopService.getAppShopList(shopDto);
    }

    @PostMapping("getAppShopByTypeId")
    @ApiOperation("根据店家种类ID获取该种类下所有店家信息 @zy")
    public R getAppShopByTypeId(Long typeId){
        PageHelper.startPage(0,10);
        List<ShopModel> shopInfoList = shopService.getAppShopByTypeId(typeId);
        if(shopInfoList != null && shopInfoList.size() > 0){
            Page page = new Page();
            page = (Page)shopInfoList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    @PostMapping("getShopTeamListByShopId")
    @ApiOperation("根据店家ID获取该店家下的有效组队信息 @zy")
    public R getShopTeamListByShopId(Long shopId){
        PageHelper.startPage(0,10);
        List<ShopTeamsDto> shopTeamsInfoList = teamService.getShopTeamListByShopId(shopId);
        if(shopTeamsInfoList != null && shopTeamsInfoList.size() > 0){
            Page page = new Page();
            page = (Page)shopTeamsInfoList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    @PostMapping("getAppShopTypeList")
    @ApiOperation("获取店家组队种类信息 @Mr.Gx")
    public R getAppShopTypeList(){
        return R.ok(shopTypeService.findByShopType(ShopTypeModel.SHOP_TYPE_ZD));
    }

}
