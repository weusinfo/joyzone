package com.joyzone.platform.module.app.controller;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.DynamicCommentModel;
import com.joyzone.platform.core.model.DynamicModel;
import com.joyzone.platform.core.service.DynamicCommentSerivce;
import com.joyzone.platform.core.service.DynamicSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:TODO
 *
 * @author zhangyu
 * date: 2020/11/05
 */
@RestController
@RequestMapping("app_dynamic")
@Api(description = "AppDynamicController",tags = "app动态相关接口")
public class AppDynamicController {

    @Autowired
    private DynamicSerivce dynamicSerivce;
    @Autowired
    private DynamicCommentSerivce dynamicCommentSerivce;

    @ApiOperation("发布动态")
    @PostMapping("saveDynamic")
    public R saveDynamic(DynamicModel dynamicModel){
        if(dynamicModel == null || dynamicModel.getUserId() == null || dynamicModel.getKind() == null)
            return R.error("参数不能为空");

        return  dynamicSerivce.saveDynamic(dynamicModel) > 0 ? R.ok() : R.error("发布失败");
    }

    @ApiOperation("评论动态")
    @PostMapping("saveDynamicComment")
    public R saveDynamicComment(DynamicCommentModel commentModel){
        if(commentModel == null || commentModel.getUserId() == null ||
                commentModel.getDynamicId() == null || commentModel.getContent() == null)
            return R.error("参数不能为空");

        return  dynamicCommentSerivce.saveDynamicComment(commentModel) > 0 ? R.ok() : R.error("发布失败");
    }

    @ApiOperation("根据用户id获取用户动态列表")
    @PostMapping("getUserDynamicList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query")
    })
    public R getUserDynamicList(@RequestParam("userId") Long userId){
        return R.ok(dynamicSerivce.getUserDynamicList(userId));
    }



}
