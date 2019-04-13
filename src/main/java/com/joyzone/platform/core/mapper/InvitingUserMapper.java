package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.InvitingUserModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface InvitingUserMapper extends Mapper<InvitingUserModel> {

    List<InvitingUserModel> findByInvitingId(@Param("invitingId") Long invitingId);
}