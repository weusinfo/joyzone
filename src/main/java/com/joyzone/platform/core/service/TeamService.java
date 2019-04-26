package com.joyzone.platform.core.service;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.CouponDto;
import com.joyzone.platform.core.dto.TeamDto;
import com.joyzone.platform.core.mapper.ShopCouponMapper;
import com.joyzone.platform.core.mapper.TeamMapper;
import com.joyzone.platform.core.model.ShopCouponModel;
import com.joyzone.platform.core.model.TeamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TeamService extends BaseService<TeamModel> {

    @Autowired
    private TeamMapper teamMapper;

    public  List<TeamDto> getTeamList(TeamModel teamModel){
        return teamMapper.getTeamList(teamModel);
    }

}
