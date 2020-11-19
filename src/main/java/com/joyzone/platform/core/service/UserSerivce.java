package com.joyzone.platform.core.service;

import com.alibaba.fastjson.JSON;
import com.joyzone.platform.common.utils.RedisColumn;
import com.joyzone.platform.common.utils.RedisGeoUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.UserMapper;
import com.joyzone.platform.core.model.UserModel;

import cn.hutool.crypto.digest.DigestUtil;
import com.joyzone.platform.core.vo.LocationVO;
import io.jsonwebtoken.lang.Collections;
import tk.mybatis.mapper.entity.Example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:TODO
 *
 * @author Mr.Gx
 * date: 2019/3/29
 */
@Service
@Transactional
public class UserSerivce extends BaseService<UserModel> {

    @Autowired
    private RedisService redisService;
    
    @Autowired
    private ChatService chatService;

    @Resource
    private UserMapper userMapper;

    /**
     * 后台用户管理清单
     * @param userModel
     * Mr.Gx
     */
    public List<UserModel> getUserList(UserModel userModel){
        return  userMapper.getUserList(userModel);
    }

    /**
     * 添加用户信息
     * @param userModel
     * Mr.Gx
     */
    public int saveUser(UserModel userModel){
        Date date = new Date();
        if(userModel.getId() != null){
            userModel.setUpdateTime(date);
            int i = userMapper.updateByPrimaryKeySelective(userModel);
            if(i > 0) {
            	chatService.updateUser(""+userModel.getId(), userModel.getUserName());
            }
            return i;
        }
        userModel.setCreateTime(date);
        userModel.setUpdateTime(date);
        return userMapper.saveUser(userModel);
    }

    /**
     * 根据电话号码获取用户信息
     * Mr.Gx
     */
    public UserModel getUserByPhone(String phone){
        return userMapper.getUserByPhone(phone);
    }

    /**
     * 添加用户的经纬度信息
     * Mr.Gx
     */
    public void saveUserLngAndLat(Long userId,Double lng,Double lat){
        // RedisGeoUtil.geoadd(redisService.getStringRedisTemplate(), RedisColumn.USER_LOCATION,new Point(lng,lat),userId.toString());
        redisService.hset(RedisColumn.USER_LOCATION , userId.toString() ,JSON.toJSON(new LocationVO(lng,lat)));
    }

    /**
     * 批量删除数据
     * @param ids
     * Mr.Gx
     */
    public int delUsers(Long[] ids){
        return userMapper.delUsers(ids);
    }

    /**
     * 根据条件获取导出数据
     * @param userModel
     * Mr.Gx
     */
    public List<UserModel> getExportUserXls(UserModel userModel){
        return userMapper.getUserList(userModel);
    }


    /**
     * 根据条件获取用户信息
     * @param userId
     * Mr.Gx
     */
    public UserModel getUserInfo(Long userId){
        return userMapper.getUserInfo(userId);
    }
    
    public Integer updateChatMD5(Long userId, String md5) {
    	return userMapper.updateChatMD5(userId, md5);
    }
    
    public void updateMD5() {
    	Example example = new Example(UserModel.class);
    	example.createCriteria().andIsNull("chatIdMd5");
    	List<UserModel> users = selectByExample(example);
    	if(!Collections.isEmpty(users)) {
    		for(UserModel user : users) {
    			String md5 = DigestUtil.md5Hex(user.getId().toString());
    			updateChatMD5(user.getId(), md5);
    		}
    	}
    }

}
