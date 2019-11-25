package com.joyzone.platform.module.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.ShopDto;
import com.joyzone.platform.core.dto.ShopHomeDto;
import com.joyzone.platform.core.dto.ShopInfoDto;
import com.joyzone.platform.core.dto.ShopTeamsDto;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.model.ShopCollectModel;
import com.joyzone.platform.core.model.ShopModel;
import com.joyzone.platform.core.model.ShopTypeModel;
import com.joyzone.platform.core.service.ShopCollectService;
import com.joyzone.platform.core.service.ShopService;
import com.joyzone.platform.core.service.ShopTypeService;
import com.joyzone.platform.core.service.TeamService;
import io.swagger.annotations.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private ShopCollectService shopCollectService;

    /*@PostMapping("getAppShopHomeList")
    @ApiOperation("商家首页信息展示 @Mr.Gx")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "form"),
            @ApiImplicitParam(name = "pageSize",value = "三组商家信息显示数量,默认6条",paramType = "form",defaultValue = "6")
    })
    public R getAppShopHomeList(@RequestParam("userId") Long userId, @RequestParam("pageSize") Integer pageSize){
        return R.ok(shopService.getAppShopHomeList(userId,pageSize));
    }*/

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
    @ApiOperation("查看商家詳情信息 @Mr.Gx")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "店家ID",paramType = "form"),
            @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "form")
    })
    public R findByShopId(Long id,Long userId){
        ShopInfoDto shopInfoDto = shopService.findShopInfoDtoByShopId(id,userId);
        if(shopInfoDto != null){
            return R.ok(shopInfoDto);
        }
        return R.error("未获取到数据！");
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

    @PostMapping("saveShopCollect")
    @ApiOperation("收藏或取消收藏店家 @zy")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "form"),
            @ApiImplicitParam(name = "shopId",value = "店家ID",paramType = "form"),
            @ApiImplicitParam(name = "type",value = "0：收藏；1取消收藏",paramType = "form")
    })
    public R saveShopCollect(@RequestParam("userId") Long userId,@RequestParam("shopId") Long shopId,@RequestParam("type") Integer type){
        List<ShopCollectModel> shopCollects = shopCollectService.getShopCollectByConditions(userId,shopId);
        if(shopCollects == null || shopCollects.size() == 0){
            if(type == 1){
                return R.error("未收藏过该店家！无法取消");
            }
            if(type == 0){
                ShopCollectModel shopCollectModel = new ShopCollectModel();
                shopCollectModel.setUserId(userId);
                shopCollectModel.setShopId(shopId);
                shopCollectModel.setStatus(1); //收藏成功
                shopCollectModel.setCreateTime(new Date());
                int ret = shopCollectService.save(shopCollectModel);
                if(ret == 1){
                    return R.ok("收藏成功！");
                }else {
                    return R.error("收藏失败！");
                }
            }
        }
        if(shopCollects.size() == 1){
            ShopCollectModel bean = shopCollects.get(0);
            if(type == 1){
                if(bean.getStatus() == 1){
                    bean.setStatus(0);
                    bean.setUpdateTime(new Date());
                    int ret = shopCollectService.update(bean);
                    if (ret == 1) {
                        return R.ok("操作成功！");
                    } else {
                        return R.error("操作失败！");
                    }
                }
                if(bean.getStatus() == 0){
                    return R.error("未收藏过该店家！无法取消");
                }
            }
            if(type == 0) {
                if(bean.getStatus() == 1){
                    return R.error("已收藏过该店家！");
                }
                if(bean.getStatus() == 0){
                    bean.setStatus(1);
                    bean.setUpdateTime(new Date());
                    int ret = shopCollectService.update(bean);
                    if (ret == 1) {
                        return R.ok("操作成功！");
                    } else {
                        return R.error("操作失败！");
                    }
                }
            }
        }
        return R.error("数据库脏数据！");
    }
    
    /**
     * 根据商户类型获取该类型的部落
     * @param userId
     * @param typeId
     * @return
     */
    @PostMapping("/getGroupByTypeId")
    public R getGroupIdByTypeId(@RequestParam("userId") Long userId, @RequestParam("typeId") Long typeId) {
    	String groupId = shopTypeService.getGroupIdByTypeId(typeId);
    	if(StringUtils.isNotEmpty(groupId)) {
    		Map<String, Object> groupMap = Maps.newHashMap();
    		groupMap.put("groupId", groupId);
    		return R.ok(groupMap);
    	}
		return R.error("系统错误, 该商户类型没有部落");
    	
    }


}
