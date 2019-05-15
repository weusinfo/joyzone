package com.joyzone.platform.core.service;

import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.common.utils.RedisColumn;
import com.joyzone.platform.common.utils.RedisGeoUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.UserMapper;
import com.joyzone.platform.core.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

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
            return userMapper.updateByPrimaryKeySelective(userModel);
        }
        userModel.setCreateTime(date);
        userModel.setUpdateTime(date);
        return userMapper.saveUser(userModel);
    }

    /**
     * 根据电话号码获取用户信息
     * @param userModel
     * Mr.Gx
     */
    public List<UserModel> getUserByPhone(String phone){
        return userMapper.getUserByPhone(phone);
    }

    /**
     * 添加用户的经纬度信息
     * Mr.Gx
     */
    public Long saveUserLngAndLat(Long userId,Double lng,Double lat){
        return RedisGeoUtil.geoadd(redisService.getStringRedisTemplate(), RedisColumn.USER_LOCATION,new Point(lng,lat),userId.toString());
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
}
