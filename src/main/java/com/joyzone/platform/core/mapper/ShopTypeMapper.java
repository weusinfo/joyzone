package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ShopTypeModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ShopTypeMapper extends Mapper<ShopTypeModel> {

    //获取店家类型
    List<Map<String,Object>> findByShopType();

    //获取店家类型ID获取旗下的种类信息
    List<Map<String,Object>> findByPid(@Param("pid") Long pid);
}