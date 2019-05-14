package com.joyzone.platform.core.service;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.ShopDiscountMapper;
import com.joyzone.platform.core.model.ShopDiscountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopDiscountService extends BaseService<ShopDiscountModel> {

    @Autowired
    private ShopDiscountMapper shopDiscountMapper;

    /**
     * 折扣券清单
     * Mr.Gx
     */
    public List<ShopDiscountModel> getShopDiscountList(ShopDiscountModel shopDiscountModel){
        return shopDiscountMapper.getShopDiscountList(shopDiscountModel);
    }

    public int delShopDiscounts(Long[] ids ){
        return shopDiscountMapper.delShopDiscounts(ids);
    }
}
