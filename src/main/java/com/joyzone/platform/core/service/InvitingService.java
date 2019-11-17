package com.joyzone.platform.core.service;

import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.InvitingDto;
import com.joyzone.platform.core.mapper.InvitingMapper;
import com.joyzone.platform.core.mapper.InvitingUserMapper;
import com.joyzone.platform.core.model.*;
import com.joyzone.platform.core.vo.AppInvitingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InvitingService extends BaseService<InvitingModel> {

    @Autowired
    private InvitingMapper invitingMapper;
    @Autowired
    private InvitingUserMapper invitingUserMapper;
    @Autowired
    private GroupService groupService;

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
            return R.error("ID不能空");
        return R.ok(invitingMapper.selectByPrimaryKey(id));
    }

    public List<InvitingModel> selectInvitingList(InvitingModel invitingModel){
        return invitingMapper.getInvitingList(invitingModel);
    }

    /**
     * 前端个人发起邀请
     * @param invitingModel
     * Mr.Gx
     */
    public int saveInviting(InvitingModel invitingModel){
        invitingModel.setStatus(0);  //邀约有效
        invitingModel.setResult(2);  //邀约时间未到，还在邀约中
        invitingModel.setCreateTime(new Date());
        //todo 个人邀请时创建聊天群
        /*String groupId = groupService.createTeamGroup(teamModel.getShopId());
        teamModel.setChatGroupId(groupId);*/
        int flag = invitingMapper.saveInviting(invitingModel);
        List<InvitingModel> invitingList = invitingMapper.checkUserStartInviting(invitingModel.getOwner(),invitingModel.getContent(),invitingModel.getStartTime());
        if(invitingList == null || invitingList.size() == 0){
            return 0;
        }
        int res = saveInvitingUsers(invitingModel,invitingList);
        if(res == 0){
            return 111;
        }
        return flag;
    }
    public int saveInvitingUsers(InvitingModel invitingModel,List<InvitingModel> invitingList){
        InvitingUserModel invitingUserModel = new InvitingUserModel();
        invitingUserModel.setInvitingId(invitingList.get(0).getId());
        invitingUserModel.setUserId(invitingModel.getOwner());
        invitingUserModel.setStatus(0);  //加入
        invitingUserModel.setCreateTime(new Date());
        invitingUserModel.setUpdateTime(new Date());
        int flag = invitingUserMapper.insertSelective(invitingUserModel);
        return flag;
    }

    /**
     * APP获取受邀列表
     * Mr.Gx
     */
    public R getUserToInvitings(InvitingDto invitingDto){
        if(invitingDto == null)
            return R.error("参数不能为空.");
        if(invitingDto.getUserId() == null)
            return R.error("用户ID不能为空.");
        if(invitingDto.getPageNum() == null)
            invitingDto.setPageNum(BaseModel.PAGE_NUM);
        if(invitingDto.getPageSize() == null)
            invitingDto.setPageSize(BaseModel.PAGE_SIZE);

        return pageToRet(invitingMapper.getUserToInvitings(invitingDto));
    }

    /**
     * APP获取我的正式函列表
     * Mr.Gx
     */
    public R getConfirmInvitings(InvitingDto invitingDto){
        if(invitingDto.getUserId() == null)
            return R.error("用户ID不能为空.");
        if(invitingDto.getPageNum() == null)
            invitingDto.setPageNum(BaseModel.PAGE_NUM);
        if(invitingDto.getPageSize() == null)
            invitingDto.setPageSize(BaseModel.PAGE_SIZE);

        return pageToRet(invitingMapper.getConfirmInvitings(invitingDto));
    }

    /**
     * APP获取我的邀请列表
     * Mr.Gx
     */
    public R getMyInvitings(InvitingDto invitingDto){
        if(invitingDto == null)
            return R.error("参数不能为空.");
        if(invitingDto.getUserId() == null)
            return R.error("用户ID不能为空.");
        if(invitingDto.getPageNum() == null)
            invitingDto.setPageNum(BaseModel.PAGE_NUM);
        if(invitingDto.getPageSize() == null)
            invitingDto.setPageSize(BaseModel.PAGE_SIZE);

        return pageToRet(invitingMapper.getMyInvitings(invitingDto));
    }

    /**
     * APP获取回应我的邀请列表
     * Mr.Gx
     */
    public R getRespondInvitings(InvitingDto invitingDto){
        if(invitingDto == null)
            return R.error("参数不能为空.");
        if(invitingDto.getUserId() == null)
            return R.error("用户ID不能为空.");
        if(invitingDto.getPageNum() == null)
            invitingDto.setPageNum(BaseModel.PAGE_NUM);
        if(invitingDto.getPageSize() == null)
            invitingDto.setPageSize(BaseModel.PAGE_SIZE);

        return pageToRet(invitingUserMapper.getRespondInvitings(invitingDto));
    }

    /**
     * 统一处理分页数据
     * @param list
     * @return
     */
    private R pageToRet(List<AppInvitingVO> list){
        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    public Integer agreeOrNotTheInviting(InvitingDto invitingDto){
        InvitingUserModel invitingUserModel = new InvitingUserModel();
        invitingUserModel.setInvitingId(invitingDto.getInvitingId());
        invitingUserModel.setUserId(invitingDto.getUserId());
        invitingUserModel.setStatus(0);  //加入
        invitingUserModel.setCreateTime(new Date());
        return invitingUserMapper.agreeOrNotTheInviting(invitingUserModel);
    }

    public Integer sendFinalInviting(InvitingDto invitingDto){
        List<InvitingUserModel> invitingUserModels = invitingUserMapper.getInvitingUserByIds(invitingDto);
        if(invitingUserModels == null || invitingUserModels.size() != 1){
            return 0;
        }
        Long invitingUserId = invitingUserModels.get(0).getId();
        InvitingUserModel invitingUserModel = new InvitingUserModel();
        invitingUserModel.setId(invitingUserId);
        /*invitingUserModel.setConfirm(0);*/  //收到邀请者的正式函
        invitingUserModel.setUpdateTime(new Date());
        return invitingUserMapper.updateByPrimaryKeySelective(invitingUserModel);
    }

    public Map<String,Object> checkInvitingIfSuccess(Long invitingId){
        return invitingMapper.checkInvitingIfSuccess(invitingId);
    }
    
    public String isInvitingOwner(Long invitingId, Long userId) {
    	return invitingMapper.checkInvitingOwner(invitingId, userId);
    }
    
    public int updateChatGroupId(Long invitingId, String groupId) {
    	return invitingMapper.updateChatGroupId(invitingId, groupId);
    }
    
    public String getChatGroupId(Long invitingId) {
    	return invitingMapper.getGroupId(invitingId);
    }

}
