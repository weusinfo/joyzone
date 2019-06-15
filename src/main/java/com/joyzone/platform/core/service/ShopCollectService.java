package com.joyzone.platform.core.service;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.ShopCollectMapper;
import com.joyzone.platform.core.model.ShopCollectModel;
import com.joyzone.platform.core.model.ShopModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class ShopCollectService extends BaseService<ShopCollectModel> {

    @Autowired
    private ShopCollectMapper shopCollectMapper;

    public List<ShopModel> getMyShopCollectList(ShopCollectModel model,Long userId){
        return shopCollectMapper.getMyShopCollectList(model,userId);
    }

    public List<ShopCollectModel> getShopCollectByConditions(Long userId,Long shopId){
        return shopCollectMapper.getShopCollectByConditions(userId,shopId);
    }
}
