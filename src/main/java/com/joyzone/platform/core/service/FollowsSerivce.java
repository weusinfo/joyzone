package com.joyzone.platform.core.service;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.UserFollowDTO;
import com.joyzone.platform.core.mapper.FollowsMapper;
import com.joyzone.platform.core.model.FollowsModel;
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
public class FollowsSerivce extends BaseService<FollowsModel> {
    @Autowired
    private FollowsMapper followsMapper;

    /**
     * 关注(别人)/取消关注
     * @param userId targetId
     * zhangyu
     */
    public int saveFollows(Long userId,Long targetId,Integer type){
        FollowsModel followsModel = followsMapper.findFollowRecord(userId,targetId);
        if(type == 0){
            if(followsModel != null){
                followsModel.setStatus(0);
                followsModel.setUpdateTime(new Date());
                return followsMapper.updateByPrimaryKey(followsModel);
            } else {
                followsModel = new FollowsModel();
                followsModel.setUserId(userId);
                followsModel.setTargetId(targetId);
                followsModel.setStatus(0);
                followsModel.setCreateTime(new Date());
                followsModel.setUpdateTime(new Date());
                return followsMapper.insert(followsModel);
            }
        } else if(type == 1){
            followsModel.setStatus(1);
            followsModel.setUpdateTime(new Date());
            if(followsModel != null){
                return followsMapper.updateByPrimaryKey(followsModel);
            }
        }
        return 0;
    }

    /**
     * 根据用户id获取关注者列表与被关注者列表
     * @param userId
     * zhangyu
     */
    public List<UserFollowDTO> getUserFollowList(Long userId, Integer type){
        return followsMapper.getUserFollowList(userId,type);
    }

}
