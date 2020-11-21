package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.*;
import com.joyzone.platform.core.model.TeamModel;
import com.joyzone.platform.core.vo.AppTeamVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TeamMapper extends Mapper<TeamModel> {
    /*List<TeamDto> getTeamList(TeamModel teamModel,@Param("userId") Long userId, @Param("sort") Integer sort);*/
    List<TeamDto> getTeamList(TeamModel teamModel,@Param("userId") Long userId);

    List<ActivityDTO> getActivityList(@Param("userId") Long userId, @Param("type") Integer type);

    Map<String,Object> checkTeamIfSuccess(@Param("teamId") Long teamId);

    List<TeamDto> getTeamList(TeamModel teamModel);

    List<AppTeamVO> getAppTeamList(@Param("userId") Long userId,
                                   @Param("pageNum") Integer pageNum,
                                   @Param("pageSize") Integer pageSize);

    List<ShopTeamsDto> getShopTeamListByShopId(@Param("shopId") Long shopId);

    List<TeamModel> checkUserStartTeam(@Param("userId") Long userId,@Param("shopId") Long shopId);

    TeamRuleDto getTeamRuleList(@Param("teamId")  Long teamId,@Param("userId") Long userId);
    
    Integer checkTeamOwner(@Param("teamId") Long teamId, @Param("userId") Long userId);
    
    String getGroupId(@Param("teamId") Long teamId);

    List<TeamModel> checkTeamSaveSuccess(@Param("userId") Long userId,@Param("chatGroupId") String chatGroupId);

    List<String> queryJoinUserImgs(@Param("teamId") Long teamId);

    ActivityDetailDTO getActivityDetail(@Param("teamId") Long teamId);

    List<ActivityUserDTO> queryJoinUserInfos(@Param("teamId") Long teamId);

    List<ActivityDTO> getOrderList(@Param("userId") Long userId, @Param("type") Integer type);

    ShopDetaiDTO getShopTabOne(@Param("shopId") Long shopId, @Param("userId") Long userId);

    List<String> queryShopPicList(@Param("shopId") Long shopId);

    List<ActivityDTO> getShopActivityList(@Param("shopId") Long shopId);
}