package com.joyzone.platform.core.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.DateUtils;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.common.utils.RedisUtil;
import com.joyzone.platform.common.utils.ThreadLocalMap;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.*;
import com.joyzone.platform.core.mapper.TeamMapper;
import com.joyzone.platform.core.mapper.TeamUsersMapper;
import com.joyzone.platform.core.model.*;
import com.joyzone.platform.core.vo.AppTeamVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TeamService extends BaseService<TeamModel> {

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private TeamUsersMapper teamUsersMapper;
    @Autowired
    private GroupService groupService;
    @Autowired
    ChatService chatService;
    @Autowired
    private TeamUsersService teamUsersService;
    
    @Autowired
    private RedisUtil redisUtil;

    /*public  List<TeamDto> getTeamList(TeamModel teamModel,Long userId, Integer sort){
        return teamMapper.getTeamList(teamModel,userId,sort);
    }*/
    private R pageToRet(List<TeamDTO> list){
        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }
    
    public  R getTeamList(TeamModel teamModel,Long userId){
        PageHelper.startPage(teamModel.getPageNum(), teamModel.getPageSize());
        return pageToRet(teamMapper.getTeamList(teamModel,userId));
    }

    public Map<String,Object> checkTeamIfSuccess(Long teamId){
        return teamMapper.checkTeamIfSuccess(teamId);
    }

    /**
     * 添加或更新组队信息
     * @param teamModel
     * @return
     */
    public int saveTeam(TeamModel teamModel){
        Date date = new Date();
        if(teamModel.getId() == null){//添加
            Integer count = teamMapper.countUserStartTeam(teamModel.getOwner(),teamModel.getShopId());
            if(count != null && count > 0){
                return 999;
            }
            teamModel.setType(ShopTypeModel.SHOP_TYPE_ZD);  //组队店家
            teamModel.setStatus(0); //组队有效
            teamModel.setCreateTime(date);
            teamModel.setResult(0); //组队中
           //添加组队时创建聊天群
           String groupId = groupService.createTeamGroup(teamModel.getShopId());
           teamModel.setChatGroupId(groupId);
           chatService.joinTeamGroup(groupId, teamModel.getOwner());
           int flag =  teamMapper.insertSelective(teamModel);
           ThreadLocalMap.put("chatGroupId", groupId);
           count = teamMapper.countUserStartTeam(teamModel.getOwner(),teamModel.getShopId());
           if(count == null || count == 0){
        	   return 0;
           }
           List<TeamModel> teamList = Lists.newArrayList();
           teamList.add(teamModel);
           int res = saveTeamUsers(teamModel,teamList);
           if(res == 0){
                return 111;
           }
           return flag;
        }
        //更新
        teamModel.setUpdateTime(date);
        return teamMapper.updateByPrimaryKeySelective(teamModel);
    }
    public int saveTeamUsers(TeamModel teamModel,List<TeamModel> teamList){
        TeamUsersModel teamUsersModel = new TeamUsersModel();
        teamUsersModel.setTeamId(teamList.get(0).getId());
        teamUsersModel.setUserId(teamModel.getOwner());
        teamUsersModel.setStatus(0);
        teamUsersModel.setCreateTime(new Date());
        teamUsersModel.setUpdateTime(new Date());
        int flag = teamUsersMapper.insertSelective(teamUsersModel);
        return flag;
    }

    /**
     * app获取组队信息列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public R getAppTeamList(Long userId,Integer pageNum,Integer pageSize){
        List<AppTeamVO> list = teamMapper.getAppTeamList(userId,pageNum,pageSize);
        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,list);
    }

    public List<ShopTeamsDTO> getShopTeamListByShopId(Long shopId){
        return teamMapper.getShopTeamListByShopId(shopId);
    }

    public TeamRuleDTO getTeamRuleList(Long teamId, Long userId){
        return teamMapper.getTeamRuleList(teamId,userId);
    }

    public boolean isTeamOwner(Long teamId, Long userId) {
    	Integer i = teamMapper.checkTeamOwner(teamId, userId);
    	if(i != null && i.equals(1)) return true;
    	return false;
    }
    
    public String getGroupId(Long teamId) {
    	return teamMapper.getGroupId(teamId);
    }

    /**
     * 添加或更新组队信息
     * @param teamModel
     * @return
     */
    public int saveActivity(TeamModel teamModel){
        Date date = new Date();
        if(teamModel.getId() == null){//添加
            if(teamModel.getShopId() != null) {
                Integer count = teamMapper.countUserStartTeam(teamModel.getOwner(), teamModel.getShopId());
                if (count != null && count > 0) {
                    return 999;
                }
            } 
            teamModel.setType(ShopTypeModel.SHOP_TYPE_ZD);  //组队店家
            teamModel.setStatus(0); //组队中
            teamModel.setCreateTime(date);
            //chatService.joinTeamGroup(groupId, teamModel.getOwner());
            if(teamModel.getInvitedId() != null){
                teamModel.setTag(0); //特约聚会
            } else {
                if(teamModel.getToWay() != 0){
                    teamModel.setTag(1); //好友聚会
                } else {
                    teamModel.setTag(2); //普通聚会
                }
            }
            teamMapper.save(teamModel);
            List<TeamModel> teamList = Lists.newArrayList();
            teamList.add(teamModel);
            saveTeamUsers(teamModel,teamList);
            String groupId = chatService.createTeamGroup(teamModel.getOwner(), teamModel.getActivityName(), "个人建群");
            if(groupId == null) {
            	throw new JZException("群组创建失败...");
            }
            ThreadLocalMap.put("chatGroupId", groupId);
            teamModel.setChatGroupId(groupId);
            teamMapper.updateChatGroupId(groupId, teamModel.getId());//更新塞回聊天群ID
            redisUtil.set(Constants.KEY_EXPIRATION_PREFIX+teamModel.getId(), null, DateUtils.getExpireTime(teamModel.getStartTime()));
        }
        //更新
        teamModel.setUpdateTime(date);
        int i = teamMapper.updateByPrimaryKeySelective(teamModel);
         return i;
    }

    public  R getActivityList(Long userId,Integer type,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<ActivityDTO> list = teamMapper.getActivityList(userId,type);

        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    public R getActivityDetail(Long userId,Long teamId){
        List userIdList = new ArrayList();
        ActivityDetailDTO detailDto = teamMapper.getActivityDetail(teamId);
        Long owner = detailDto.getUserId();
        int status = detailDto.getStatus();
        List<ActivityUserDTO> userInfoList = detailDto.getJoinUserInfoList();
        for(int i=0;i<userInfoList.size();i++){
            userIdList.add(userInfoList.get(i).getUserId());
        }
        if(status == 0){
            if(userId == owner){
                detailDto.setButtonShow("解散");
            } else if (userIdList.contains(userId)) {
                detailDto.setButtonShow("退出");
            } else {
                detailDto.setButtonShow("加入");
            }
        }else if(status == 1){
            detailDto.setButtonShow("成功"); //聚会详情按钮内容为“成功”，“失败”，“失败”时，按钮不显示在前端页面
        }else if(status == 2){
            detailDto.setButtonShow("失败");
        }else if(status == 3){
            detailDto.setButtonShow("已解散");
        }
        return R.ok(detailDto);
    }

    public int detailButton(Long userId,TeamModel teamModel,Integer type){
        int flag = 0;
        if(type == 0){ //用户加入聚会
            teamModel.setUpdateTime(new Date());
            teamMapper.updateByPrimaryKey(teamModel);
            // join the chat group
            String groupId = getGroupId(teamModel.getId());
            chatService.joinTeamGroup(groupId,userId);
            // team_users表的变更
            TeamUsersModel bean = teamUsersMapper.checkUserInTeam(new TeamUsersModel(),userId,teamModel.getId());
            if(bean == null){
                bean = new TeamUsersModel();
                bean.setTeamId(teamModel.getId());
                bean.setUserId(userId);
                bean.setStatus(0);
                bean.setCreateTime(new Date());
                int ret = teamUsersService.save(bean);
                if(ret == 1){
                    flag++;
                }
            } else {
                bean.setStatus(0);
                bean.setUpdateTime(new Date());
                int ret = teamUsersService.update(bean);
                if(ret == 1){
                    flag++;
                }
            }
            // 检查聚会人数，看是否需要更改状态为成功
            Map<String,Object> teamInfo = checkTeamIfSuccess(teamModel.getId());
            Integer number = (Integer) teamInfo.get("number");
            Integer joinNum = Integer.parseInt(teamInfo.get("joinNum").toString());
            if(number == joinNum){
                teamModel.setStatus(1); //聚会标志：成功
                teamModel.setUpdateTime(new Date());
                update(teamModel);
            }
        }
        if(type == 1) { //用户退出聚会
            teamModel.setStatus(0);  //聚会标志：进行中
            teamModel.setUpdateTime(new Date());
            teamMapper.updateByPrimaryKey(teamModel);
            //todo backoff the chat group 需要操作群聊吗,一起思考下必要性？
            //groupService.deleteGroup(teamModel.getId());
            // team_users表的变更
            TeamUsersModel bean = teamUsersMapper.checkUserInTeam(new TeamUsersModel(),userId,teamModel.getId());
            bean.setStatus(1);
            bean.setUpdateTime(new Date());
            int ret = teamUsersService.update(bean);
            if(ret == 1){
                flag++;
            }
        }
        if(type == 2) { //发起者解散聚会
            teamModel.setStatus(3); //聚会标志：已解散
            teamModel.setUpdateTime(new Date());
            teamMapper.updateByPrimaryKey(teamModel);
            //todo backoff the chat group 需要操作群聊吗,一起思考下必要性？
            //groupService.cancelGroup(teamModel.getId(), userId);
            // team_users表的变更
            TeamUsersModel bean = teamUsersMapper.checkUserInTeam(new TeamUsersModel(),userId,teamModel.getId());
            bean.setStatus(1);
            bean.setUpdateTime(new Date());
            int ret = teamUsersService.update(bean);
            if(ret == 1){
                flag++;
            }
        }
        return flag;
    }

    public R getOrderList(Long userId,Integer type,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<ActivityDTO> list = teamMapper.getOrderList(userId,type);
        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    public R getShopTabOne(Long shopId,Long userId){
        ShopDetaiDTO shopDetaiDTO = teamMapper.getShopTabOne(shopId,userId);
        return R.ok(shopDetaiDTO);
    }
    
    public int updateChatGroupId(String chatGroupId, Long id) {
    	return teamMapper.updateChatGroupId(chatGroupId, id);
    }
    
    public int failInviting(String invitingId) {
    	return teamMapper.failInviting(invitingId);
    }

}
