package com.joyzone.platform.module.app.controller;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.DynamicCommentModel;
import com.joyzone.platform.core.model.DynamicModel;
import com.joyzone.platform.core.service.DynamicCommentSerivce;
import com.joyzone.platform.core.service.DynamicSerivce;
import com.joyzone.platform.core.service.FollowsSerivce;
import com.joyzone.platform.core.vo.DynamicFormVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private FollowsSerivce followsSerivce;

    @ApiOperation("发布动态")
    @PostMapping("saveDynamic")
    public R saveDynamic(@RequestBody DynamicModel dynamicModel){
        if(dynamicModel == null || dynamicModel.getUserId() == null || dynamicModel.getKind() == null)
            return R.error("参数不能为空");

        return  dynamicSerivce.saveDynamic(dynamicModel) > 0 ? R.ok() : R.error("发布失败");
    }

    @ApiOperation("评论动态")
    @PostMapping("saveDynamicComment")
    public R saveDynamicComment(@RequestBody DynamicCommentModel commentModel){
        if(commentModel == null || commentModel.getUserId() == null ||
                commentModel.getDynamicId() == null || commentModel.getContent() == null)
            return R.error("参数不能为空");

        return  dynamicCommentSerivce.saveDynamicComment(commentModel) > 0 ? R.ok() : R.error("评论失败");
    }

    @ApiOperation("根据用户id获取用户动态列表")
    @PostMapping("getUserDynamicList")
    public R getUserDynamicList(@RequestBody DynamicFormVO dynamicFormVO){
        if(dynamicFormVO == null || dynamicFormVO.getUserId() == null)
            return R.error("参数不能为空");
        return R.ok(dynamicSerivce.getUserDynamicList(dynamicFormVO.getUserId()));
    }

    @ApiOperation("获取动态首页动态列表")
    @PostMapping("getIndexDynamicList")
    public R getIndexDynamicList(@RequestBody DynamicFormVO dynamicFormVO){
        if(dynamicFormVO == null || dynamicFormVO.getUserId() == null || dynamicFormVO.getType() == null)
            return R.error("参数不能为空");
        return R.ok(dynamicSerivce.getIndexDynamicList(dynamicFormVO.getUserId(),dynamicFormVO.getType()));
    }

    @ApiOperation("点赞(动态)/取消点赞")
    @PostMapping("giveThumb")
    public R giveThumb(@RequestBody DynamicFormVO dynamicFormVO){
        if(dynamicFormVO == null || dynamicFormVO.getUserId() == null ||
                dynamicFormVO.getType() == null || dynamicFormVO.getDynamicId() == null)
            return R.error("参数不能为空");
        DynamicModel dynamicModel = dynamicSerivce.selectByPrimaryKey(dynamicFormVO.getDynamicId());
        if(dynamicModel == null){
            return R.error("动态不存在");
        }
        return  dynamicSerivce.giveThumb(dynamicFormVO.getUserId(),dynamicModel,dynamicFormVO.getType()) > 0 ? R.ok() : R.error("点赞(动态)/取消点赞失败");
    }

    @ApiOperation("关注(别人)/取消关注")
    @PostMapping("saveFollows")
    public R saveFollows(@RequestBody DynamicFormVO dynamicFormVO){
        if(dynamicFormVO == null || dynamicFormVO.getUserId() == null ||
                dynamicFormVO.getType() == null || dynamicFormVO.getTargetId() == null)
            return R.error("参数不能为空");
        return  followsSerivce.saveFollows(dynamicFormVO.getUserId(),dynamicFormVO.getTargetId(),dynamicFormVO.getType()) > 0 ? R.ok() : R.error("关注/取消关注失败");
    }

    @ApiOperation("根据用户id获取关注者列表与被关注者列表")
    @PostMapping("getUserFollowList")
    public R getUserFollowList(@RequestBody DynamicFormVO dynamicFormVO){
        if(dynamicFormVO == null || dynamicFormVO.getUserId() == null || dynamicFormVO.getType() == null)
            return R.error("参数不能为空");
        return R.ok(followsSerivce.getUserFollowList(dynamicFormVO.getUserId(),dynamicFormVO.getType()));
    }

}
