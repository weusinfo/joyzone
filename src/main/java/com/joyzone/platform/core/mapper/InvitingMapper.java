package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.InvitingModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface InvitingMapper extends Mapper<InvitingModel> {

    List<InvitingModel> getInvitingList(InvitingModel invitingModel);
}