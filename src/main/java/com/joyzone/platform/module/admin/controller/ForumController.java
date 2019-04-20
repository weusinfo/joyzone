package com.joyzone.platform.module.admin.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.ForumModel;
import com.joyzone.platform.core.model.InvitingModel;
import com.joyzone.platform.core.service.ForumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/forum")
@Api(tags="后台论坛管理",description="ForumController")
public class ForumController {

    @Autowired
    private ForumService forumService;

    /**
     * Mr.Gx
     */
    @GetMapping("getForumList")
    @ApiOperation("获取论坛清单")
    public R getForumList(ForumModel forumModel){
       List<ForumModel> list = forumService.getForumList(forumModel);
       if(list != null && list.size() > 0){
           Page page = new Page();
           page = (Page)list;
           return R.pageToData(page.getTotal(),page.getResult());
       }
       return  R.pageToData(0L,new ArrayList<>());
    }

    @GetMapping("/exportForumModelXls")
    @ApiOperation("论坛清单导出")
    public void exportForumModelXls(ForumModel forumModel, HttpServletResponse response) throws Exception{
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(Constants.JOY_FORUM, "UTF-8") + ".xls");
        response.setCharacterEncoding("UTF-8");
        List<ForumModel> list = forumService.getForumList(forumModel);
        ExportParams params = new ExportParams(Constants.JOY_FORUM, Constants.JOY_FORUM);
        Workbook workbook = ExcelExportUtil.exportExcel(params, InvitingModel.class, list);
        workbook.write(response.getOutputStream());
    }
}
