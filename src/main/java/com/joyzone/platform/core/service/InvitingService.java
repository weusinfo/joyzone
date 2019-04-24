package com.joyzone.platform.core.service;


import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.InvitingDto;
import com.joyzone.platform.core.mapper.InvitingMapper;
import com.joyzone.platform.core.mapper.InvitingUserMapper;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.model.InvitingModel;
import com.joyzone.platform.core.model.InvitingUserModel;
import com.joyzone.platform.core.vo.AppInvitingVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
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
    public R saveInviting(InvitingModel invitingModel){
        if(invitingModel == null)
            return R.error("参数不能为空.");
        if(invitingModel.getOwner() == null)
            return R.error("发起人ID不能为空.");
        if(StringUtils.isBlank(invitingModel.getContent()))
            return R.error("邀请主题不能为空.");
        if(invitingModel.getType() == null)
            return R.error("邀请类型不能为空.");
        if(StringUtils.isBlank(invitingModel.getAddress()))
            return R.error("邀请地址不能为空.");
        if(invitingModel.getStartTime() == null)
            return R.error("主题进行时间不能为空.");
        if(invitingModel.getShopId() != null){
            if(StringUtils.isBlank(invitingModel.getShopName())){
                return R.error("店家名称不能为空.");
            }
        }
        invitingModel.setCreateTime(new Date());
        return invitingMapper.insertSelective(invitingModel) > 0 ?
                R.ok("成功发起") : R.error("操作失败");
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
}
