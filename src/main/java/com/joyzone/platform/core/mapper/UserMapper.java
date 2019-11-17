package com.joyzone.platform.core.mapper;


import com.joyzone.platform.core.model.UserModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<UserModel> {

    //用户清单
    List<UserModel> getUserList(UserModel userModel);

    UserModel getUserByPhone(@Param("phone") String phone);

    int delUsers(@Param("ids") Long[] ids);

    UserModel getUserInfo(@Param("userId") Long userId);
    
    int saveUser(UserModel userModel);
    
    Integer updateChatMD5(@Param("userId") Long userId, @Param("md5") String md5);
}