package com.joyzone.platform.core.mapper;


import com.joyzone.platform.core.model.UserModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<UserModel> {

    //用户清单
    List<UserModel> getUserList(UserModel userModel);
}