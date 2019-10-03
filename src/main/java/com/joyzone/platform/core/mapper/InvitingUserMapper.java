package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.InvitingDto;
import com.joyzone.platform.core.model.InvitingUserModel;
import com.joyzone.platform.core.vo.AppInvitingVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface InvitingUserMapper extends Mapper<InvitingUserModel> {

    List<InvitingUserModel> findByInvitingId(@Param("invitingId") Long invitingId);

    List<AppInvitingVO> getRespondInvitings(InvitingDto invitingDto);

    Integer agreeOrNotTheInviting(InvitingUserModel invitingUserModel);

    List<InvitingUserModel> getInvitingUserByIds(InvitingDto invitingDto);

    InvitingUserModel checkUserInInviting(InvitingUserModel model,@Param("userId") Long userId, @Param("teamOrInvitingId") Long teamOrInvitingId);

}