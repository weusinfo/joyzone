package com.joyzone.platform.module.app.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.common.utils.ThreadLocalMap;
import com.joyzone.platform.core.dto.TeamDto;
import com.joyzone.platform.core.dto.TeamRuleDto;
import com.joyzone.platform.core.model.ShopTypeModel;
import com.joyzone.platform.core.model.TeamModel;
import com.joyzone.platform.core.model.TeamUsersModel;
import com.joyzone.platform.core.model.UserModel;
import com.joyzone.platform.core.service.ChatService;
import com.joyzone.platform.core.service.GroupService;
import com.joyzone.platform.core.service.ShopTypeService;
import com.joyzone.platform.core.service.TeamService;
import com.joyzone.platform.core.service.TeamUsersService;
import com.joyzone.platform.core.service.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app_team")
@Api(tags = "app首页店家组队列表相关接口",description = "AppTeamController")
public class AppTeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamUsersService teamUsersService;
    @Autowired
    private UserSerivce userSerivce;
    
    @Autowired
    private ShopTypeService typeService;
    
    @Autowired
    private ChatService chatService;


    /*@PostMapping("/getTeamList")
    @ApiOperation("前端获取店家組隊列表 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "0:热点 1：最新", required = true, dataType = "Integer", paramType = "query")
    })
    public R getTeamList(TeamModel teamModel,Long userId, Integer sort){
        PageHelper.startPage(0,10);
        List<TeamDto> teamList = teamService.getTeamList(teamModel,userId,sort);
        if(teamList != null && teamList.size() > 0){
            Page page = new Page();
            page = (Page)teamList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }*/

    @PostMapping("/getTeamList")
    @ApiOperation("新版：前端获取店家組隊列表。快组完的队排前，且按startTime升序 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query")
    })
    public R getTeamList(TeamModel teamModel,Long userId){
        return teamService.getTeamList(teamModel,userId);
        /*if(teamList != null && teamList.size() > 0){
            Page page = new Page();
            page = (Page)teamList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());*/
    }

    @PostMapping("/joinTheTeam")
    @ApiOperation("前端用户报名已有组队 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "teamId", value = "组队ID", required = true, dataType = "Long", paramType = "query")
    })
    public R joinTheTeam(TeamUsersModel model,Long userId,Long teamId){
        UserModel userModel = userSerivce.selectByKey(userId);
        if(userModel ==null || userModel.getSex() == null || userModel.getUserName() == null || userModel.getBirthday() == null){
            return R.error(100,"请完善个人必要信息：昵称/性别/生日");
        }
        TeamUsersModel teamUsersModel = teamUsersService.checkUserInTeam(model,userId,teamId);
        if(teamUsersModel != null && teamUsersModel.getStatus() == 0){
            return R.error("用户已报名该组队！");
        }
        if(teamUsersModel != null && teamUsersModel.getStatus() == 1){
            teamUsersModel.setStatus(0);
            teamUsersModel.setUpdateTime(new Date());
            int result = teamUsersService.update(teamUsersModel);
            checkTeamIfSuccess(teamId);
            if(result == 1){
                return R.ok("用户报名成功！");
            }else {
                return R.error("用户报名失败！");
            }
        }
        String groupId = teamService.getGroupId(teamId);
        chatService.joinTeamGroup(groupId,userId);// join the chat group
        TeamUsersModel bean = new TeamUsersModel();
        bean.setTeamId(teamId);
        bean.setUserId(userId);
        bean.setStatus(0);
        bean.setCreateTime(new Date());
        int ret = teamUsersService.save(bean);
        if(ret == 1){
        	checkTeamIfSuccess(teamId);
        	Map<String,String> map = Maps.newHashMap();
        	map.put("chatGroupId", groupId);
            return R.ok("用户报名成功！");
        }else {
            return R.error("用户报名失败！");
        }
    }

    public void checkTeamIfSuccess(Long teamId){
        Map<String,Object> teamInfo = teamService.checkTeamIfSuccess(teamId);
        Integer number = (Integer) teamInfo.get("number");
        Integer joinNum = Integer.parseInt(teamInfo.get("joinNum").toString());
        if(number == joinNum){
            TeamModel model = new TeamModel();
            model.setId(teamId);
            model.setResult(1);
            model.setUpdateTime(new Date());
            teamService.update(model);
        }
    }
  
    @PostMapping("/saveTeam")
    @ApiOperation("前端用户发起组队信息 @Mr.Gx")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query")
    })
    public R saveTeam(TeamModel model,Long userId){
        UserModel userModel = userSerivce.selectByKey(userId);
        if(userModel ==null || userModel.getSex() == null || userModel.getUserName() == null || userModel.getBirthday() == null){
            return R.error(100,"请完善个人必要信息：昵称/性别/生日");
        }
        if(model == null)
            return R.error("系统参数不能为空");
        if(model.getShopId() == null)
            return R.error("所属店家ID不能为空");
        if(model.getStartTime() == null)
            return R.error("开始时间不能为空");
        if(model.getNumber() == null || model.getNumber() < 2)
            return R.error("请填写限制人数,且人数大于1");
        if(model.getPayWay() == null)
            return R.error("请填写付款方式");
        if(model.getSexWant() == null)
            return R.error("请填写希望的男女比列");
        model.setOwner(userId);
        int ret = teamService.saveTeam(model);
        if(ret == 999){
            return R.error("用户已在该店内发起了有效组队！");
        }
        if(ret == 111){
            return R.error("用户组队后保存team_user失败！");
        }
        Map<String,String> map = Maps.newHashMap();
        map.put("chatGroupId", (String)ThreadLocalMap.get("chatGroupId"));
        return ret > 0 ? R.ok(map) : R.error("操作失败");
    }

    /*@PostMapping("/getAppTeamList")
    @ApiOperation("App获取组队列表信息 @Mr.Gx")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数",required = true, dataType = "Integer",defaultValue = "1",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",required = true, dataType = "Integer",defaultValue = "10",paramType = "query")
    })
    public R getAppTeamList(Long userId,Integer pageNum,Integer pageSize){
        return teamService.getAppTeamList(userId,pageNum,pageSize);
    }*/

    @PostMapping("/getTeamRuleInfo")
    @ApiOperation("首页组队列表点击规则后的页面 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teamId", value = "组队id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Long", paramType = "query")
    })
    public R getTeamRuleInfo(Long teamId,Long userId){
        TeamRuleDto teamRuleDto = teamService.getTeamRuleList(teamId,userId);
        if(teamRuleDto == null){
            return R.error("没有数据！");
        }
        return R.ok(teamRuleDto);
    }

    /**
     * 获取部落列表
     */
    @ApiOperation("获取有效的部落")
    @PostMapping("/getTribes")
    public R getTribes() {
    	List<Map<String,Object>> tribes = typeService.getTribes();
    	if(PublicUtil.isNotEmpty(tribes)) {
    		return R.ok(tribes);
    	}
    	return R.error("还没有部落");
    }

}
