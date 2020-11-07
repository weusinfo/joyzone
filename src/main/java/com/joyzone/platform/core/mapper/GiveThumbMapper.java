package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.GiveThumbModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GiveThumbMapper extends Mapper<GiveThumbModel> {
    GiveThumbModel findThumbRecord(@Param("userId") Long userId,@Param("dynamicId") Long dynamicId);

}