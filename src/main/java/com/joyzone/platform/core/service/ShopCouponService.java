package com.joyzone.platform.core.service;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.ShopCouponMapper;
import com.joyzone.platform.core.model.ShopCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopCouponService extends BaseService<ShopCouponModel> {

    @Autowired
    private ShopCouponMapper shopCouponMapper;

    /**
     * 体验券清单列表
     * @param shopCouponModel
     * @return
     */
    public List<ShopCouponModel> getShopCouponList(ShopCouponModel shopCouponModel){
        return shopCouponMapper.getShopCouponList(shopCouponModel);
    }

    /**
     * 体验券更改状态
     * Mr.Gx
     */
    public R updateShopCouponStatus(Long id,Integer status){
        ShopCouponModel shopCouponModel = shopCouponMapper.selectByPrimaryKey(id);
        if(shopCouponModel == null)
            return R.error(R.STATUS_FAIL,"当前体验券不存在了,请刷新界面.");
        if(shopCouponModel.getStatus() != null && shopCouponModel.getStatus() == status)
            return R.error(R.STATUS_FAIL,"体验券已是当前状态,请刷新界面.");

        shopCouponModel.setStatus(status);
        shopCouponModel.setUpdateTime(new Date());
        return shopCouponMapper.updateByPrimaryKeySelective(shopCouponModel) > 0 ?
                 R.ok("更新成功") : R.error(R.STATUS_FAIL,"更新失败");
    }

}
