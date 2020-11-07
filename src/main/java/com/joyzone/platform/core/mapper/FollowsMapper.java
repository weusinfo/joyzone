package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.UserFollowDto;
import com.joyzone.platform.core.model.FollowsModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FollowsMapper extends Mapper<FollowsModel> {
    List<UserFollowDto> getUserFollowList(@Param("userId") Long userId, @Param("type") Integer type);

    FollowsModel findFollowRecord(@Param("userId") Long userId,@Param("targetId") Long targetId);
}