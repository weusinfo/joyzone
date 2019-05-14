package com.joyzone.platform.module.app.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.TeamDto;
import com.joyzone.platform.core.model.TeamModel;
import com.joyzone.platform.core.model.TeamUsersModel;
import com.joyzone.platform.core.model.UserModel;
import com.joyzone.platform.core.service.TeamService;
import com.joyzone.platform.core.service.TeamUsersService;
import com.joyzone.platform.core.service.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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


    @PostMapping("/getTeamList")
    @ApiOperation("前端获取店家組隊列表 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sort", value = "0:热点 1：最新", required = true, dataType = "Integer", paramType = "query")
    })
    public R getTeamList(TeamModel teamModel, Integer sort){
        PageHelper.startPage(0,10);
        List<TeamDto> teamList = teamService.getTeamList(teamModel,sort);
        if(teamList != null && teamList.size() > 0){
            Page page = new Page();
            page = (Page)teamList;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
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
        TeamUsersModel bean = new TeamUsersModel();
        bean.setTeamId(teamId);
        bean.setUserId(userId);
        bean.setStatus(0);
        bean.setCreateTime(new Date());
        int ret = teamUsersService.save(bean);
        checkTeamIfSuccess(teamId);
        if(ret == 1){
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
            R.error("系统参数不能为空");
        if(model.getOwner() == null)
            R.error("发起人的ID不能为空");
        if(model.getShopId() == null)
            R.error("所属店家ID不能为空");
        if(model.getStartTime() == null)
            R.error("开始时间不能为空");
        if(model.getNumber() == null)
            R.error("请填写限制人数");
        if(model.getPayWay() == null)
            R.error("请填写付款方式");
        if(model.getSexWant() == null)
            R.error("请填写希望的男女比列");
        int ret = teamService.saveTeam(model);
        if(ret == 999){
            return R.error("用户已在该店内发起了有效组队！");
        }
        return ret > 0 ? R.ok() : R.error("操作失败");
    }

    @PostMapping("/getAppTeamList")
    @ApiOperation("App获取组队列表信息 @Mr.Gx")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页数",required = true, dataType = "Integer",defaultValue = "1",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",required = true, dataType = "Integer",defaultValue = "10",paramType = "query")
    })
    public R getAppTeamList(Long userId,Integer pageNum,Integer pageSize){
        return teamService.getAppTeamList(userId,pageNum,pageSize);
    }
}
