package com.joyzone.platform.core.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.joyzone.platform.common.utils.LocationUtils;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.common.utils.RedisColumn;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.*;
import com.joyzone.platform.core.mapper.DynamicMapper;
import com.joyzone.platform.core.mapper.DynamicPictureMapper;
import com.joyzone.platform.core.mapper.GiveThumbMapper;
import com.joyzone.platform.core.model.DynamicModel;
import com.joyzone.platform.core.model.DynamicPictureModel;
import com.joyzone.platform.core.model.GiveThumbModel;
import com.joyzone.platform.core.vo.LocationVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

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
    private RedisService redisService;
    @Resource
    private DynamicMapper dynamicMapper;
    @Resource
    private DynamicPictureMapper dynamicPictureMapper;
    @Resource
    private GiveThumbMapper thumbMapper;

    /**
     * 发布动态
     * @param dynamicDTO
     * zhangyu
     */
    public int saveDynamic(DynamicDTO dynamicDTO){

        DynamicModel dynamicModel = new DynamicModel(dynamicDTO);

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
    public UserDynamicDTO getUserDynamicList(Long userId,Integer pageNum,Integer pageSize){
        UserDynamicDTO userDynamic = dynamicMapper.selectByUserDynamic(userId);
        if (null != userDynamic){
            List<UserDynamicListDto> list = dynamicMapper.queryUserDynamicList(userId,pageNum,pageSize);
            if  (!list.isEmpty()){
                list.stream().forEach(m -> {
                    String pics = m.getPics();
                    if  (StringUtils.isNotBlank(pics)){
                        m.setDynamicPics(JSONObject.parseArray(pics,String.class));
                        m.setPics(null);
                    }
                });
                userDynamic.setUserDynamicList(list);
            }
        }
        return userDynamic;
    }

    public List<IndexDynamicListDTO> getIndexDynamicList(Long userId, Integer type, Integer pageNum, Integer pageSize){
        // 获取当前用户的坐标
        LocationVO userLocation = this.selectByUserLocation(userId);
        double lat = 0.0,lnt = 0.0;
        if (null != userLocation){
            lat = userLocation.getLat();
            lnt = userLocation.getLnt();
        }
        // 获取
        List<IndexDynamicListDTO> list = dynamicMapper.getIndexDynamicList(userId,pageNum,pageSize);
        double lat1 = lat, lnt1 = lnt;
        if (null != list && !list.isEmpty()){
            list.stream().forEach(m -> {
                LocationVO locatio = this.selectByUserLocation(m.getUserId());
                double lat2 = 0.0,lnt2 = 0.0;
                if (null != locatio){
                    lat2 = locatio.getLat();
                    lnt2 = locatio.getLnt();
                }
                // 计算距离
                m.setDistance(LocationUtils.getDistance(lat1,lnt1,lat2,lnt2));
                if(StringUtils.isNotBlank(m.getPics())){
                    m.setDynamicPics(JSONObject.parseArray(m.getPics(),String.class));
                } else {
                    m.setDynamicPics(new ArrayList<>());
                }
                m.setPics(null);
            });
        }
        // 根据距离进行排序
        Collections.sort(list, new Comparator<IndexDynamicListDTO>(){
            public int compare(IndexDynamicListDTO arg0, IndexDynamicListDTO arg1) {
                BigDecimal hits0 = BigDecimal.valueOf(arg0.getDistance());
                BigDecimal hits1 = BigDecimal.valueOf(arg1.getDistance()) ;
                if (hits1.compareTo(hits0) > 0) {
                   return -1;
                } else if (hits1.compareTo(hits0) == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return list;
    }

    /**
     * 获取用户当前的经纬度做出相应的判断
     * @param userId
     * @return
     */
    private LocationVO selectByUserLocation(Long userId){
        if (null == userId){
           return null;
        }
        // 获取当前用户的经纬度
        Object obj = this.redisService.hget(RedisColumn.USER_LOCATION,userId.toString());
        if (null != obj){
           return JSON.parseObject(obj.toString(),LocationVO.class);
        }
        return null;
    }

    public DynamicModel selectByPrimaryKey(Long dynamicId){
        return dynamicMapper.selectByPrimaryKey(dynamicId);
    }

    public int giveThumb(Long userId,DynamicModel dynamicModel,Integer type){
        GiveThumbModel thumbModel = thumbMapper.findThumbRecord(userId,dynamicModel.getId());
        if(type == 0){
            if(thumbModel != null){
                thumbModel.setStatus(0);
                thumbModel.setUpdateTime(new Date());
                thumbMapper.updateByPrimaryKey(thumbModel);
            } else {
                thumbModel = new GiveThumbModel();
                thumbModel.setUserId(userId);
                thumbModel.setDynamicId(dynamicModel.getId());
                thumbModel.setStatus(0);
                thumbModel.setCreateTime(new Date());
                thumbModel.setUpdateTime(new Date());
                thumbMapper.insert(thumbModel);
            }
            dynamicModel.setThumbs(dynamicModel.getThumbs() + 1);
        } else if(type == 1){
            thumbModel.setStatus(1);
            thumbModel.setUpdateTime(new Date());
            if(thumbModel != null){
                thumbMapper.updateByPrimaryKey(thumbModel);
            }
            dynamicModel.setThumbs(dynamicModel.getThumbs() - 1);
        }
        return dynamicMapper.updateByPrimaryKey(dynamicModel);
    }

}
