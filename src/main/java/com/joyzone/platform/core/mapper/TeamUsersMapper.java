package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.TeamUsersModel;
import com.joyzone.platform.core.model.UserModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeamUsersMapper extends Mapper<TeamUsersModel> {

    //根据组队ID获取加入的人员信息
    List<UserModel> getTeamUsers(@Param("teamId") Long teamId,@Param("pageNum")
                                    Integer pageNum,@Param("pageSize") Integer pageSize);

    TeamUsersModel checkUserInTeam(TeamUsersModel model,@Param("userId") Long userId, @Param("teamId") Long teamId);

}