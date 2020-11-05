package com.joyzone.platform.core.service;

import cn.hutool.crypto.digest.DigestUtil;
import com.joyzone.platform.common.utils.RedisColumn;
import com.joyzone.platform.common.utils.RedisGeoUtil;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.UserDynamicDto;
import com.joyzone.platform.core.mapper.DynamicMapper;
import com.joyzone.platform.core.mapper.DynamicPictureMapper;
import com.joyzone.platform.core.mapper.UserMapper;
import com.joyzone.platform.core.model.DynamicModel;
import com.joyzone.platform.core.model.DynamicPictureModel;
import com.joyzone.platform.core.model.UserModel;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Description:TODO
 *
 * @author zhangyu
 * date: 2020/11/05
 */
@Service
@Transactional
public class DynamicSerivce extends BaseService<DynamicModel> {
    @Autowired
    private DynamicMapper dynamicMapper;
    @Autowired
    private DynamicPictureMapper dynamicPictureMapper;

    /**
     * 发布动态
     * @param dynamicModel
     * zhangyu
     */
    public int saveDynamic(DynamicModel dynamicModel){
        dynamicModel.setThumbs(0);
        dynamicModel.setCreateTime(new Date());
        dynamicModel.setUpdateTime(new Date());
        int res = dynamicMapper.insert(dynamicModel);
        if (res > 0) {
            //res = insertDynamicPicture(dynamicModel);
        }
        return res;
    }

    private int insertDynamicPicture(DynamicModel dynamicModel){
        DynamicPictureModel pictureModel = new DynamicPictureModel();
        pictureModel.setDynamicId(dynamicModel.getId());
        pictureModel.setPictureUrl(dynamicModel.getDynamicPics());
        pictureModel.setCreateTime(new Date());
        pictureModel.setUpdateTime(new Date());
        return dynamicPictureMapper.insert(pictureModel);
    }

    /**
     * 根据用户id获取用户动态列表
     * @param userId
     * zhangyu
     */
    public List<UserDynamicDto> getUserDynamicList(Long userId){
        return dynamicMapper.getUserDynamicList(userId);
    }

}
