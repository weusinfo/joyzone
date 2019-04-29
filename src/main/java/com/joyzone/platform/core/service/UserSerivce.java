package com.joyzone.platform.core.service;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.UserMapper;
import com.joyzone.platform.core.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description:TODO
 *
 * @author Mr.Gx
 * date: 2019/3/29
 */
@Service
@Transactional
public class UserSerivce extends BaseService<UserModel> {

    @Autowired
    private UserMapper userMapper;

    /**
     * 后台用户管理清单
     * @param userModel
     * Mr.Gx
     */
    public List<UserModel> getUserList(UserModel userModel){
        return  userMapper.getUserList(userModel);
    }

    /**
     * 添加用户信息
     * @param userModel
     * Mr.Gx
     */
    public int saveUser(UserModel userModel){
        return userMapper.insertSelective(userModel);
    }

    public List<UserModel> getUserByPhone(String phone){
        return userMapper.getUserByPhone(phone);
    }
}
