package com.joyzone.platform.core.mapper;


import com.joyzone.platform.core.model.UserModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<UserModel> {

    //用户清单
    List<UserModel> getUserList(UserModel userModel);

    List<UserModel> getUserByPhone(@Param("phone") String phone);

    int delUsers(@Param("ids") Long[] ids);
    
    int saveUser(UserModel userModel);
}