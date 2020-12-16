package com.joyzone.platform.core.service;

import com.alibaba.fastjson.JSON;
import com.joyzone.platform.common.exception.JZException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    
    @Autowired
    private CacheService cacheService;

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
            cacheService.delUser(userModel.getId()+"");
            if(i > 0) {
            	chatService.updateUser(""+userModel.getId(), userModel.getUserName());
            }
            return i;
        }
        userModel.setCreateTime(date);
        userModel.setUpdateTime(date);
        int i =userMapper.saveUser(userModel);
        cacheService.apdUser(userModel);
        return i;
    }
    
    /*
     * 注册合并，避免数据库插入和环信注册中有一方失败。
     */
    public void register(UserModel userModel) {
    	 String chatPwd = DigestUtil.md5Hex(userModel.getId().toString());
         userModel.setChatIdMd5(chatPwd);
         this.saveUser(userModel);
         Object obj = chatService.registerUser(userModel.getId().toString(), chatPwd);
         if(obj ==null) {
        	 throw new JZException("注册环信ID失败.");
         }
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
        int i = userMapper.delUsers(ids);
        String[] idArr = new String[ids.length];
        for(int j = 0; j<ids.length;j++) {
        	idArr[j] = ids[j] + "";
        }
        cacheService.delUser(idArr);
        return i;
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
    
    @Override
    public UserModel selectByKey(Object userId) {
    	UserModel userModel = cacheService.getUserById(userId.toString());
    	if(userModel == null) {
    		userModel = super.selectByKey(userId);
    		cacheService.apdUser(userModel);
    	}
    	return userModel;
    }

}
