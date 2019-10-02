package com.joyzone.platform.core.service;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.InvitingUserMapper;
import com.joyzone.platform.core.model.InvitingUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InvitingUserService extends BaseService<InvitingUserModel> {

    @Autowired
    private InvitingUserMapper invitingUserMapper;

    public InvitingUserModel checkUserInInviting(InvitingUserModel model,Long userId, Long teamOrInvitingId){
        return invitingUserMapper.checkUserInInviting(model,userId,teamOrInvitingId);
    }


}
