package com.joyzone.platform.core.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.UserMapper;
import com.joyzone.platform.core.model.UserModel;


@Service
public class SystemService  extends BaseService<UserModel> {

	@Resource
    private UserMapper userMapper;
	
	/**
     * 记录注册失败的用户
     * @param userModel
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveFailedUser(UserModel userModel) {
    	userMapper.saveFailedUser(userModel);
    }
}
