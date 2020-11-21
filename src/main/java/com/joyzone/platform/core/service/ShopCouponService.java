package com.joyzone.platform.core.service;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.CouponDTO;
import com.joyzone.platform.core.dto.CouponRuleDTO;
import com.joyzone.platform.core.mapper.ShopCouponMapper;
import com.joyzone.platform.core.model.ShopCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
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

    public List<Map<String,Object>> getCouponShopList(ShopCouponModel shopCouponModel){
        return shopCouponMapper.getCouponShopList(shopCouponModel);
    }

    public  List<CouponDTO> getCouponList(ShopCouponModel shopCouponModel, Long userId, Integer sort){
        return shopCouponMapper.getCouponList(shopCouponModel,userId,sort);
    }

    public Map<String,Object> checkCouponIfSuccess(Long couponId){
        return shopCouponMapper.checkCouponIfSuccess(couponId);
    }

    public int delCoupons(Long[] ids){
        return shopCouponMapper.delCoupons(ids);
    }

    public CouponRuleDTO getCouponRuleInfo(Long couponId, Long userId){
        return shopCouponMapper.getCouponRuleInfo(couponId,userId);
    }

    public int updChatGroupId(String groupId, Long couponId) {
    	return shopCouponMapper.updChatGroupId(groupId, couponId);
    }
    
    public Map<String,Object> getCouponInfo(Long couponId) {
    	return shopCouponMapper.getCouponInfo(couponId);
    }
}
