package com.joyzone.platform.core.service;

import com.alibaba.fastjson.JSONObject;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.ShopCommentDTO;
import com.joyzone.platform.core.mapper.ShopCommentMapper;
import com.joyzone.platform.core.model.ShopCommentModel;
import com.joyzone.platform.core.vo.IndexShopCommentVO;
import com.joyzone.platform.core.vo.ShopCommentVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCommentService extends BaseService<ShopCommentModel> {

    @Resource
    private ShopCommentMapper shopCommentMapper;

    /**
     * 获取某个商家的评论信息
     * @param shopId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public R listShopComments(Long shopId,Integer pageNum,Integer pageSize){

        IndexShopCommentVO vo = new IndexShopCommentVO();
        // 商家评论类型统计
        vo.setCommentCount(this.shopCommentMapper.countShopComment(shopId));
        // 商家评论列表
        List<ShopCommentVO> list = this.shopCommentMapper.listShopComments(shopId,pageNum,pageSize);
        if (!list.isEmpty()){
            list.stream().forEach(m -> {
                String pics = m.getPics();
                if (StringUtils.isNotBlank(pics)){
                    m.setPicUrls(JSONObject.parseArray(pics,String.class));
                }
                m.setPics(null);
            });
        }
        vo.setComments(list);

        return R.ok(vo);
    }

    /**
     *  评价操作
     * @param shopCommentDTO
     * @return
     */
    public R create(ShopCommentDTO shopCommentDTO){
        // 参数校验
        R r = this.checkParam(shopCommentDTO);
        if (null != r){
            return r;
        }
        ShopCommentModel model = new ShopCommentModel(shopCommentDTO);
        model.setPicUrls(this.getPics(shopCommentDTO.getPicUrls()));
        try {
            shopCommentMapper.insert(model);
            return R.ok();
        }catch (Exception e){
            return R.error();
        }
    }

    private String getPics(String picturlUrls){
        if (StringUtils.isBlank(picturlUrls)){
            return new ArrayList<>().toString();
        }
        String[] pics = picturlUrls.split(",");
        return JSONObject.toJSONString(pics);
    }

    private R checkParam(ShopCommentDTO shopCommentDTO){
        if (null == shopCommentDTO){
           return R.error("参数不能为空");
        }
        if (null == shopCommentDTO.getShopId()){
            return R.error("商家参数不能为空");
        }
        if (null == shopCommentDTO.getUserId()){
            return R.error("评论人不能为空");
        }
        if (StringUtils.isBlank(shopCommentDTO.getContent())){
            return R.error("评论内容不能为空");
        }
        return null;
    }
}
