package com.joyzone.platform.core.service;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.PhoneBlackMapper;
import com.joyzone.platform.core.model.PhoneBlackModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zy on 2019/4/29.
 */

@Service
@Transactional
public class PhoneBlackService extends BaseService<PhoneBlackModel> {

    private Logger logger = LoggerFactory.getLogger(PhoneBlackService.class);
    @Autowired
    private PhoneBlackMapper phoneBlackMapper;

    public PhoneBlackModel isBlack(String phone){
        return phoneBlackMapper.isBlack(phone);
    }

}
