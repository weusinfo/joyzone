package com.joyzone.platform.module.app.controller;


import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.TeamDto;
import com.joyzone.platform.core.model.TeamModel;
import com.joyzone.platform.core.model.TeamUsersModel;
import com.joyzone.platform.core.service.TeamService;
import com.joyzone.platform.core.service.TeamUsersService;
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


    @PostMapping("/getTeamList")
    @ApiOperation("前端获取店家組隊列表 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sort", value = "0:热点 1：最新", required = true, dataType = "Integer", paramType = "query")
    })
    public R getTeamList(TeamModel teamModel, Integer sort){
        List<TeamDto> teamList = teamService.getTeamList(teamModel);
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
        TeamUsersModel teamUsersModel = teamUsersService.checkUserInTeam(model,userId,teamId);
        if(teamUsersModel != null && teamUsersModel.getStatus() == 0){
            return R.error("用户已报名该组队！");
        }
        if(teamUsersModel != null && teamUsersModel.getStatus() == 1){
            teamUsersModel.setStatus(0);
            teamUsersModel.setUpdateTime(new Date());
            int result = teamUsersService.update(teamUsersModel);
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
        if(ret == 1){
            return R.ok("用户报名成功！");
        }else {
            return R.error("用户报名失败！");
        }
    }

}
