package com.joyzone.platform.core.service;


import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.ShopTypeMapper;
import com.joyzone.platform.core.model.ShopTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopTypeService extends BaseService<ShopTypeModel> {

    @Autowired
    private ShopTypeMapper shopTypeMapper;

    /**
     * 获取店家类型ID与名称
     * Mr.Gx
     */
    public List<Map<String,Object>> findByShopType(Integer type){
        return shopTypeMapper.findByShopType(type);
    }

    /**
     * 获取店家类型ID与名称
     * Mr.Gx
     */
    public List<Map<String,Object>> findByPid(Long pid){
        return shopTypeMapper.findByPid(pid);
    }

    /**
     * 获取店家类型信息
     * Mr.Gx
     */
    public List<ShopTypeModel> selectByPageSize(Integer pageNum,Integer pageSize,Integer type){
        return shopTypeMapper.selectByPageSize(pageNum,pageSize,type);
    }

}
