package com.joyzone.platform.module.app.controller;

import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.util.StringUtil;
import com.joyzone.platform.common.utils.Constants;
import com.joyzone.platform.common.utils.PublicUtil;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.common.utils.SMSUtil;
import com.joyzone.platform.core.model.PhoneBlackModel;
import com.joyzone.platform.core.model.UserModel;
import com.joyzone.platform.core.service.ChatService;
import com.joyzone.platform.core.service.PhoneBlackService;
import com.joyzone.platform.core.service.RedisService;
import com.joyzone.platform.core.service.UserSerivce;

import cn.hutool.crypto.digest.DigestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/app_loginApi")
@Api(tags = "app注册登录相关接口",description = "AppLoginApiController")
public class AppLoginApiController {
	
	private Logger LOGGER = LoggerFactory.getLogger(AppLoginApiController.class);

    @Autowired
    private PhoneBlackService phoneBlackService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserSerivce userSerivce;
    
    @Autowired
    private ChatService chatService;
    
    @Autowired
    private SMSUtil smsUtil;

    /**
     * zy
     * 获取手机验证码
     **/
    @PostMapping(value = "/getPhoneVcode")
    @ApiOperation(value = "获取手机验证码 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机号", required = true, dataType = "String", paramType = "query")
    })
    public R getPhoneVcode(@RequestParam("phone") String phone) {
        if (phone == null) {
            return R.error("手机号不能为空.");
        }
        Map map = new HashMap();
        int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
        if (isMobileNO(phone)) {
            //判断手机号是否属于黑名单
            PhoneBlackModel phoneBlack = phoneBlackService.isBlack(phone);
            if (phoneBlack != null) {
                map.put("type", 1);
                map.put("identify", null);
                return R.error("此电话号码已因不当言行被拉入黑名单!");
            }
            try {
				boolean isSent = smsUtil.sendCodeSMS(phone, ""+mobile_code);
				if(!isSent) {
					return R.error("短信验证码发送已超过限制");
				}
			} catch (ClientException e) {
				LOGGER.error("Send code to phone s% error, ",e);
				return R.error("请重试");
			}
            map.put("type", 0);
            return R.ok((Object)map);
        } else {
            return R.error("手机号格式不正确!");
        }
    }

    /**
     * 验证手机号格式
     *
     * @param mobiles
     * @return
     */
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((1[3,4,5,6,7,8,9]))\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * zy
     * 用户注册
     **/
    @PostMapping(value = "/userRegister")
    @ApiOperation(value = "用户注册或登录 @zhangyu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "mobileCode", value = "验证码", required = true, dataType = "String", paramType = "query")
    })
    public R userRegister(@RequestParam("phone") String phone,@RequestParam("mobileCode") String mobileCode, String invitationCode) {
        Map map = new HashMap();
        if (StringUtil.isEmpty(phone) || StringUtil.isEmpty(mobileCode)) {
            return R.error("参数有误！");
        }
        Object obj = redisService.hget(Constants.CACHE_KEY_CODE, phone);
        if(obj == null || !mobileCode.equals((String)obj)) {
        	return R.error("验证码无效");
        }
        UserModel userModel = userSerivce.getUserByPhone(phone);
        if(userModel != null){
            map.put("message","该手机号已注册！");
            map.put("userId",userModel.getId());
            map.put("user",userModel);
            redisService.hdel(Constants.CACHE_KEY_CODE, phone);
            return R.ok((Object)map);
        }
        Long parent = null;
        if(!PublicUtil.isEmpty(invitationCode)) {
        	parent = userSerivce.getParentId(invitationCode);
        }
        userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setType(0);  //0:用户
        userModel.setStatus(0);   //用户状态: 0 激活 ， 1 封号， 2禁入
        userModel.setCreateTime(new Date());
        userModel.setParent(parent);
        int ret = userSerivce.saveUser(userModel);
        if(ret == 0){
            return R.error("用户注册失败！");
        }
        String chatPwd = DigestUtil.md5Hex(userModel.getId().toString());
        userSerivce.updateSign(userModel.getId(), chatPwd);
        chatService.registerUser(userModel.getId().toString(), chatPwd);
        userModel = userSerivce.getUserByPhone(phone);
        map.put("message","注册成功！");
        map.put("userId",userModel.getId());
        map.put("user",userModel);
        redisService.hdel(Constants.CACHE_KEY_CODE, phone);
        return R.ok((Object)map);
    }


}
