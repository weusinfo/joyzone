package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.TeamDto;
import com.joyzone.platform.core.model.TeamModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeamMapper extends Mapper<TeamModel> {

    List<TeamDto> getTeamList(TeamModel teamModel, @Param("sort") Integer sort);

}