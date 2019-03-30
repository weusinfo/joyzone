package com.joyzone.platform.module.admin.controller;

import com.joyzone.platform.common.utils.BaseUtils;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.UserModel;
import com.joyzone.platform.core.service.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:TODO
 *
 * @author Mr.Gx
 * date: 2019/3/29
 */
@RestController
@RequestMapping("/user")
@Api(value="后台用户管理V",description="后台用户管理",tags="1.0")
public class UserController {

    @Autowired
    private UserSerivce userSerivce;

    @GetMapping("getUserLis")
    @ApiOperation("用户信息清单")
    @ApiImplicitParams(
        {
            @ApiImplicitParam(name = "名称",paramType = "name",dataType = "String"),
            @ApiImplicitParam(name = "性别",paramType = "sex",dataType = "Integer"),
            @ApiImplicitParam(name = "学历",paramType = "education",dataType = "String"),
            @ApiImplicitParam(name = "状态",paramType = "status",dataType = "String"),
            @ApiImplicitParam(name = "开始时间",paramType = "startTime",dataType = "Date"),
            @ApiImplicitParam(name = "结束时间",paramType = "endTime",dataType = "Date")
        }
    )
    public List<UserModel> getUserList(UserModel userModel){
        return userSerivce.getUserList(userModel);
    }

    @PostMapping("saveUser")
    @ApiOperation("添加用户信息")
    public R saveUser(UserModel userModel){
        if(userModel == null)
            return R.error("信息不能为空.");
        if(userModel.getUserName() == null)
            return R.error("昵称不能为空.");
        if(userModel.getPhone() == null)
            return R.error("用户电话号码不能为空.");
        if(!BaseUtils.isChinaPhoneLegal(userModel.getPhone()))
            return R.error("电话号码格式有误.");
        if(userModel.getBirthday() == null)
            return R.error("生日时间不能为空.");
        if(userModel.getSex() == null)
            return R.error("性别不能为空.");

        //userSerivce.saveUser(userModel);


        return R.ok("保存成功");

    }
}
