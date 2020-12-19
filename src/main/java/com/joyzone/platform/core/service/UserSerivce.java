package com.joyzone.platform.core.service;

import com.alibaba.fastjson.JSONObject;
import com.joyzone.platform.common.exception.JZException;
import com.joyzone.platform.common.utils.RedisColumn;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.UserMapper;
import com.joyzone.platform.core.model.UserModel;

import cn.hutool.core.util.HashUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.joyzone.platform.core.vo.LocationVO;
import io.jsonwebtoken.lang.Collections;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger logger = LoggerFactory.getLogger(UserSerivce.class);

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
        if(StringUtils.isNotEmpty(userModel.getHeadPic())){
            userModel.setCoverPic(userModel.getHeadPic());
        }
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
    	 this.saveUser(userModel);
    	 String chatPwd = DigestUtil.md5Hex(userModel.getId().toString());
         userModel.setChatIdMd5(chatPwd);
         int playNum = HashUtil.intHash(userModel.getId().intValue());
         userModel.setPlayNum(playNum+"");
         updateChatMD5(userModel.getId(), chatPwd,playNum+"");
         Object obj = chatService.registerUser(userModel.getId().toString(), chatPwd);
         cacheService.apdUser(userModel);
         if(obj ==null) {
        	 cacheService.delUser(userModel.getId().toString());
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
        redisService.hset(RedisColumn.USER_LOCATION , userId.toString() ,JSONObject.toJSONString(new LocationVO(lng,lat)));
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
    
    public Integer updateChatMD5(Long userId, String md5, String playNum) {
    	return userMapper.updateChatMD5(userId, md5,playNum);
    }
    
    public void updateMD5() {
    	Example example = new Example(UserModel.class);
    	example.createCriteria().andIsNull("chatIdMd5");
    	List<UserModel> users = selectByExample(example);
    	if(!Collections.isEmpty(users)) {
    		for(UserModel user : users) {
    			String md5 = DigestUtil.md5Hex(user.getId().toString());
    			updateChatMD5(user.getId(), md5,null);
    		}
    	}
    }
    
    @Override
    public UserModel selectByKey(Object userId) {
    	UserModel userModel = cacheService.getUserById(userId.toString());
    	if(userModel == null) {
    		userModel = userMapper.getUserInfo((Long)userId);
    		if(userModel != null) {
    			cacheService.apdUser(userModel);
    		}else {
    			logger.error("=================这个"+userId+"没有查询到用户。。。");
    		}
    		
    	}
    	return userModel;
    }

    public UserModel changeCover(Long userId, String cover) {
    	int i = userMapper.changeCover(userId, cover);
    	if(i > 0) {
    		UserModel userModel = cacheService.getUserById(userId+"");
    		userModel.setCoverPic(cover);
    		cacheService.apdUser(userModel);
    		return userModel;
    	}
    	return null;
    }
    
    public boolean checkPlayNum(String playNum) {
    	Integer count = userMapper.checkPlayNum(playNum);
    	if(count != null && count > 0) {
    		return true;
    	}
    	return false;
    }
}
