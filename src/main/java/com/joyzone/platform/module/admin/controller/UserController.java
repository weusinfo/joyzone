package com.joyzone.platform.module.admin.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.BaseUtils;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.UserModel;
import com.joyzone.platform.core.service.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * Description:TODO
 *
 * @author Mr.Gx
 * date: 2019/3/29
 */
@RestController
@RequestMapping("/user")
@Api(tags="后台用户管理V",description="UserController")
public class UserController {

    @Autowired
    private UserSerivce userSerivce;

    @GetMapping("getUserLis")
    @ApiOperation("用户信息清单 @Mr.Gx")
    public R getUserList(UserModel userModel){
        List<UserModel> list = userSerivce.getUserList(userModel);
        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,list);
    }

    @PostMapping("saveUser")
    @ApiOperation("添加及更新用户信息 @Mr.Gx")
    public R saveUser(UserModel userModel){
        if(userModel == null)
            return R.error("用户信息不能为空.");
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

        return userSerivce.saveUser(userModel) > 0 ? R.ok() : R.error("添加失败");
    }

    @PostMapping("delUsers")
    @ApiOperation("批量删除 @Mr.Gx")
    public R delUsers(@RequestParam("ids") Long[] ids){
        return userSerivce.delUsers(ids) > 0 ? R.ok() : R.error("删除失败");
    }

    @GetMapping("/exportUserXls")
    @ApiOperation("用户清单导出  @Mr.Gx")
    public void exportUserXls(UserModel userModel,HttpServletResponse response) throws Exception{
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(Constants.APP_USER, "UTF-8") + ".xls");
        response.setCharacterEncoding("UTF-8");
        List<UserModel> list = userSerivce.getExportUserXls(userModel);;
        ExportParams params = new ExportParams(Constants.APP_USER, Constants.APP_USER);
        Workbook workbook = ExcelExportUtil.exportExcel(params, UserModel.class, list);
        workbook.write(response.getOutputStream());
    }
    
    /**
     * 用于更新用户注册环信时的密码用MD5
     */
    @PostMapping("/updateMD5")
    public void updateMD5() {
    	userSerivce.updateMD5();
    }
}
