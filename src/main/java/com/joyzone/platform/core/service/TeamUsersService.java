package com.joyzone.platform.core.service;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.TeamUsersMapper;
import com.joyzone.platform.core.model.TeamUsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TeamUsersService extends BaseService<TeamUsersModel> {

    @Autowired
    private TeamUsersMapper teamUsersMapper;

    public TeamUsersModel checkUserInTeam(TeamUsersModel model,Long userId,Long teamId){
        return teamUsersMapper.checkUserInTeam(model,userId,teamId);
    }

}
