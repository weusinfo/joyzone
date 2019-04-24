package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.InvitingDto;
import com.joyzone.platform.core.model.InvitingModel;
import com.joyzone.platform.core.vo.AppInvitingVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface InvitingMapper extends Mapper<InvitingModel> {

    List<InvitingModel> getInvitingList(InvitingModel invitingModel);

    List<AppInvitingVO> getUserToInvitings(InvitingDto invitingDto);

    List<AppInvitingVO> getConfirmInvitings(InvitingDto invitingDto);

    List<AppInvitingVO> getMyInvitings(InvitingDto invitingDto);
}