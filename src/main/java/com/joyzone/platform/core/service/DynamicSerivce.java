package com.joyzone.platform.core.service;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.IndexDynamicListDto;
import com.joyzone.platform.core.dto.UserDynamicDto;
import com.joyzone.platform.core.mapper.DynamicMapper;
import com.joyzone.platform.core.mapper.DynamicPictureMapper;
import com.joyzone.platform.core.mapper.GiveThumbMapper;
import com.joyzone.platform.core.model.DynamicModel;
import com.joyzone.platform.core.model.DynamicPictureModel;
import com.joyzone.platform.core.model.GiveThumbModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private GiveThumbMapper thumbMapper;

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

    public List<IndexDynamicListDto> getIndexDynamicList(Long userId, Integer type){
        return dynamicMapper.getIndexDynamicList(userId);
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
