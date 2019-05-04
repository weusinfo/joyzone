package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.PhoneBlackModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PhoneBlackMapper extends Mapper<PhoneBlackModel> {

    PhoneBlackModel isBlack(@Param("phone") String phone);

}