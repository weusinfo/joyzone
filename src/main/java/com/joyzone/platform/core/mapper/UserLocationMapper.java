package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.UserLocationModel;
import tk.mybatis.mapper.common.Mapper;

public interface UserLocationMapper extends Mapper<UserLocationModel> {

    UserLocationModel selectByUserId(Long userId);

    int update(UserLocationModel model);
}