package com.joyzone.platform.core.service;


import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.InvitingMapper;
import com.joyzone.platform.core.mapper.InvitingUserMapper;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.model.InvitingModel;
import com.joyzone.platform.core.model.InvitingUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvitingService extends BaseService<InvitingModel> {

    @Autowired
    private InvitingMapper invitingMapper;
    @Autowired
    private InvitingUserMapper invitingUserMapper;

    /**
     * 获取邀约列表
     * @param invitingModel
     * @return
     */
    public R getInvitingList(InvitingModel invitingModel){
        if(invitingModel == null)
            return R.error(R.STATUS_FAIL,"参数不能为空");
        if(invitingModel.getPageNum() == null)
            invitingModel.setPageNum(BaseModel.PAGE_NUM);
        if(invitingModel.getPageSize() == null)
            invitingModel.setPageSize(BaseModel.PAGE_SIZE);

        List<InvitingModel> list = selectInvitingList(invitingModel);
        if(list != null && list.size() > 0){
            for(InvitingModel model : list){
                List<InvitingUserModel> invitingUsers = invitingUserMapper.findByInvitingId(invitingModel.getId());
                model.setInvitingNum(invitingUsers.size());
            }
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return  R.pageToData(0L,new ArrayList<>());
    }

    /**
     * 获取邀请详情
     * @param id
     * @return
     */
    public R findById(Long id){
        if(id == null)
            return R.error(R.STATUS_FAIL,"ID不能空");
        return R.ok(invitingMapper.selectByPrimaryKey(id));
    }

    public List<InvitingModel> selectInvitingList(InvitingModel invitingModel){
        return invitingMapper.getInvitingList(invitingModel);
    }
}
