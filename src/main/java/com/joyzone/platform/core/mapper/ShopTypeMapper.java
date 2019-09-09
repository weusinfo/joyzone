package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ShopTypeModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ShopTypeMapper extends Mapper<ShopTypeModel> {

    //获取店家类型
    List<Map<String,Object>> findByShopType(@Param("type")  Integer type);

    //获取店家类型ID获取旗下的种类信息
    List<Map<String,Object>> findByPid(@Param("pid") Long pid);

    //获取种类信息
    List<ShopTypeModel> selectByPageSize(@Param("pageNum") Integer pageNum,
                                         @Param("pageSize")Integer pageSize,
                                         @Param("type")Integer type);
    
    int addShopTye(ShopTypeModel typeModel);
    
    String getGroupIdByTypeId(@Param("typeId") Long typeId);
    
    List<Map<String,Object>> getTribes();
}