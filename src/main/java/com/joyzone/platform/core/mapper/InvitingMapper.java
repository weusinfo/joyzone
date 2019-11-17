package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.InvitingDto;
import com.joyzone.platform.core.dto.UserDto;
import com.joyzone.platform.core.model.InvitingModel;
import com.joyzone.platform.core.vo.AppInvitingVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface InvitingMapper extends Mapper<InvitingModel> {

    List<InvitingModel> getInvitingList(InvitingModel invitingModel);

    List<AppInvitingVO> getUserToInvitings(InvitingDto invitingDto);

    List<AppInvitingVO> getConfirmInvitings(InvitingDto invitingDto);

    List<AppInvitingVO> getMyInvitings(InvitingDto invitingDto);

    List<InvitingModel> checkUserStartInviting(@Param("owner") Long owner,@Param("content") String content,@Param("startTime") Date startTime);

    Map<String,Object> checkInvitingIfSuccess(@Param("invitingId") Long invitingId);
    
    String checkInvitingOwner(@Param("invitingId") Long invitingId, @Param("ownerId") Long userId);
    
    int updateChatGroupId(@Param("invitingId") Long invitingId, @Param("groupId") String groupId);
    
    int saveInviting(InvitingModel invitingModel);
    
    String getGroupId(@Param("invitingId") Long invitingId);

    List<UserDto> queryInvitingUserList(@Param("invitingId") Long invitingId);
    
    int disableExpiredInviting();
    
    List<String> getDisabledInvitingGroupIds();
}