package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ShopCommentModel;
import com.joyzone.platform.core.vo.ShopCommentCountVO;
import com.joyzone.platform.core.vo.ShopCommentVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface ShopCommentMapper extends Mapper<ShopCommentModel> {

    List<ShopCommentVO> listShopComments(@Param("shopId") Long shopId,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);

    ShopCommentCountVO countShopComment(@Param("shopId") Long shopId);
 }