package com.joyzone.platform.module.admin.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.InvitingModel;
import com.joyzone.platform.core.service.InvitingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/inviting")
@Api(tags="后台邀约管理",description="InvitingController")
public class InvitingController {

    @Autowired
    private InvitingService invitingService;

    /**
     * Mr.Gx
     */
    @ApiOperation("获取邀请列表")
    @GetMapping("getInvitingList")
    public R getInvitingList(InvitingModel invitingModel){
        return invitingService.getInvitingList(invitingModel);
    }

    /**
     * Mr.Gx
     */
    @ApiOperation("获取邀请详情信息")
    @GetMapping("findById/{id}")
    public R findById(@PathVariable Long id){
        return invitingService.findById(id);
    }

    @GetMapping("/exportInvitingXls")
    @ApiOperation("发起邀请清单导出")
    public void exportInvitingXls(InvitingModel invitingModel, HttpServletResponse response) throws Exception{
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(Constants.JOY_INVITING, "UTF-8") + ".xls");
        response.setCharacterEncoding("UTF-8");
        List<InvitingModel> list = invitingService.selectInvitingList(invitingModel);
        ExportParams params = new ExportParams(Constants.JOY_INVITING, Constants.JOY_INVITING);
        Workbook workbook = ExcelExportUtil.exportExcel(params, InvitingModel.class, list);
        workbook.write(response.getOutputStream());
    }
}
