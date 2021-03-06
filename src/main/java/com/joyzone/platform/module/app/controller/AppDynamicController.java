package com.joyzone.platform.module.app.controller;

import com.github.pagehelper.PageHelper;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.dto.DynamicDTO;
import com.joyzone.platform.core.model.DynamicCommentModel;
import com.joyzone.platform.core.model.DynamicModel;
import com.joyzone.platform.core.model.UserModel;
import com.joyzone.platform.core.service.DynamicCommentSerivce;
import com.joyzone.platform.core.service.DynamicSerivce;
import com.joyzone.platform.core.service.FollowsSerivce;
import com.joyzone.platform.core.service.UserSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/app_dynamic")
@Api(description = "AppDynamicController",tags = "app动态相关接口")
public class AppDynamicController {

    @Autowired
    private DynamicSerivce dynamicSerivce;
    @Autowired
    private DynamicCommentSerivce dynamicCommentSerivce;
    @Autowired
    private FollowsSerivce followsSerivce;
    @Autowired
    private UserSerivce userSerivce;

    @ApiOperation("发布动态")
    @PostMapping("/saveDynamic")
    public R saveDynamic(DynamicDTO dynamicDTO){
        if(dynamicDTO == null
                || dynamicDTO.getUserId() == null
                    || dynamicDTO.getKind() == null)

            return R.error("参数不能为空");


        UserModel user = userSerivce.selectByKey(dynamicDTO.getUserId());
        if( null == user  || user.getSex() == null
                || StringUtils.isBlank(user.getUserName())
                    || user.getBirthday() == null
                        || StringUtils.isBlank(user.getHeadPic())){

            return R.error(100,"请完善个人必要信息：昵称/性别/生日/头像");
        }

        return  dynamicSerivce.saveDynamic(dynamicDTO) > 0 ? R.ok() : R.error("发布失败");
    }

    @ApiOperation("评论动态")
    @PostMapping("/saveDynamicComment")
    public R saveDynamicComment(DynamicCommentModel commentModel){
        if(commentModel == null || commentModel.getUserId() == null ||
                commentModel.getDynamicId() == null || commentModel.getContent() == null)
            return R.error("参数不能为空");

        return  dynamicCommentSerivce.saveDynamicComment(commentModel) > 0 ? R.ok() : R.error("评论失败");
    }

    @ApiOperation("根据用户id获取用户动态列表")
    @PostMapping("/getUserDynamicList")
    public R getUserDynamicList(@RequestParam("userId") Long userId,
                                @RequestParam("browserId") Long browserId,
                                @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){

        return R.ok(dynamicSerivce.getUserDynamicList(userId,browserId,pageNum,pageSize));
    }

    @ApiOperation("获取动态首页动态列表")
    @PostMapping("/getIndexDynamicList")
    public R getIndexDynamicList(@RequestParam("userId") Long userId,@RequestParam("type") Integer type,
                                 @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){

        return dynamicSerivce.getIndexDynamicList(userId,type,pageNum,pageSize);
    }

    @ApiOperation("点赞(动态)/取消点赞")
    @PostMapping("/giveThumb")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "dynamicId", value = "动态id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "0：点赞 1：取消点赞", required = true, dataType = "Integer", paramType = "query")
    })
    public R giveThumb(@RequestParam("userId") Long userId,@RequestParam("dynamicId") Long dynamicId,
                       @RequestParam("type") Integer type){
        DynamicModel dynamicModel = dynamicSerivce.selectByPrimaryKey(dynamicId);
        if(dynamicModel == null){
            return R.error("动态不存在");
        }
        return  dynamicSerivce.giveThumb(userId,dynamicModel,type) > 0 ? R.ok() : R.error("点赞(动态)/取消点赞失败");
    }

    @ApiOperation("关注(别人)/取消关注")
    @PostMapping("/saveFollows")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "关注者ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "targetId", value = "被关注者id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "0：关注 1：取消关注", required = true, dataType = "Integer", paramType = "query")
    })
    public R saveFollows(@RequestParam("userId") Long userId,@RequestParam("targetId") Long targetId,
                         @RequestParam("type") Integer type){
        return  followsSerivce.saveFollows(userId,targetId,type) > 0 ? R.ok() : R.error("关注/取消关注失败");
    }

    @ApiOperation("根据用户id获取关注者列表与被关注者列表")
    @PostMapping("/getUserFollowList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "0：我关注的人 1：关注我的人", required = true, dataType = "Integer", paramType = "query")
    })
    public R getUserFollowList(@RequestParam("userId") Long userId,@RequestParam("type") Integer type,
    		@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
    	PageHelper.startPage(pageNum, pageSize);
        return R.ok(followsSerivce.getUserFollowList(userId,type));
    }

    @ApiOperation("删除动态信息")
    @PostMapping("/delete")
    public R deleteDynamic(@RequestParam("dynamicId") Long dynamicId,@RequestParam("userId") Long userId){
        return R.ok(dynamicSerivce.deleteDynamic(dynamicId,userId));
    }

    @ApiOperation("根据获取动态详情")
    @PostMapping("/selectByDynamicId")
    public R selectByDynamicId(@RequestParam("userId") Long userId,@RequestParam("dynamicId") Long dynamicId){

        return R.ok(dynamicSerivce.selectByDynamicId(userId,dynamicId));
    }

    @ApiOperation("举报")
    @PostMapping("/report")
    public R reportByDynamic(@RequestParam("userId") Long userId,@RequestParam("dynamicId") Long dynamicId){
        dynamicSerivce.reportByDynamic(userId,dynamicId);
        return R.ok();
    }
}
