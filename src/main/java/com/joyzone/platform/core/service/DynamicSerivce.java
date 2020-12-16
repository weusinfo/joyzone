package com.joyzone.platform.core.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.LocationUtils;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.common.utils.RedisColumn;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.*;
import com.joyzone.platform.core.mapper.DynamicMapper;
import com.joyzone.platform.core.mapper.GiveThumbMapper;
import com.joyzone.platform.core.model.DynamicModel;
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
    private GiveThumbMapper thumbMapper;

    /**
     * 发布动态
     * @param dynamicDTO
     * zhangyu
     */
    public int saveDynamic(DynamicDTO dynamicDTO){

        DynamicModel dynamicModel = new DynamicModel(dynamicDTO);
        dynamicModel.setPics(dynamicDTO.getPicturlUrls());
        dynamicModel.setThumbs(0);
        Date date = new Date();
        dynamicModel.setCreateTime(date);
        dynamicModel.setUpdateTime(date);
        // 获取当前用户坐标
        LocationVO userLocation = this.selectByUserLocation(dynamicModel.getUserId());
        double lat = 0.0,lnt = 0.0;
        if (null != userLocation){
            lat = userLocation.getLat();
            lnt = userLocation.getLnt();
        }
        dynamicModel.setLng(new BigDecimal(lnt));
        dynamicModel.setLat(new BigDecimal(lat));
        int res = dynamicMapper.insert(dynamicModel);
        return res;
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
                    List<UserDynamicCommentListDTO> commentList = m.getCommentDetailList();
                    setReviewerInfo(commentList);
                    String pics = m.getPics();
                    if  (StringUtils.isNotBlank(pics)){
                    	String[] rtnPics = pics.split(",");
                        m.setDynamicPics(Arrays.asList(rtnPics));
                        m.setPics(null);
                    }
                });
                userDynamic.setUserDynamicList(list);
            }
        }
        return userDynamic;
    }

    public R getIndexDynamicList(Long userId, Integer type, Integer pageNum, Integer pageSize){
        // 获取当前用户的坐标
        LocationVO userLocation = this.selectByUserLocation(userId);
        double lat = 0.0,lnt = 0.0;
        if (null != userLocation){
            lat = userLocation.getLat();
            lnt = userLocation.getLnt();
        }

        List<IndexDynamicListDTO> dynamics = new ArrayList<>();
        // 获取
        List<IndexDynamicListDTO> list = null;
        if(0 == type.intValue()){ // 动态列表
            list = dynamicMapper.getIndexDynamicList(userId,pageNum,pageSize);
        }
        if(1 == type.intValue()){ // 我关注的人和关注我的人动态列表
            list = dynamicMapper.getIndexFollowDynamicList(userId,pageNum,pageSize);
        }
        if (null != list && !list.isEmpty()){
            for (IndexDynamicListDTO dto : list){
                // 如果用户动态设置了私密，并当前用户没有关注则不显示
                if (0 == type.intValue() && 1 == dto.getKind() && 0 == dto.getFollowed()){
                    continue;
                }
                List<UserDynamicCommentListDTO> commentList = dto.getCommentDetailList();
                setReviewerInfo(commentList);
                double lat2 = dto.getLat();
                double lnt2 = dto.getLnt();
                if(new BigDecimal(lnt).compareTo(new BigDecimal(lnt2)) != 0){
                    // 计算距离
                    dto.setDistance(LocationUtils.getDistance(lat,lnt,lat2,lnt2));
                }
                if(StringUtils.isNotBlank(dto.getPics())){
                    String[] rtnPics = dto.getPics().split(",");
                    dto.setDynamicPics(Arrays.asList(rtnPics));
                } else {
                    dto.setDynamicPics(new ArrayList<>());
                }
                dto.setPics(null);
                dynamics.add(dto);
            }
        }
        // 根据距离进行排序
        Collections.sort(dynamics, new Comparator<IndexDynamicListDTO>(){
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
        if (dynamics.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),dynamics);
        }
        return R.pageToData(0L,new ArrayList<>());
    }
    private void setReviewerInfo(List<UserDynamicCommentListDTO> commentList){
        commentList.stream().forEach(n -> {
            Map<String,Object> reviewerInfo = null;
            if (n.getPid() != null){
                reviewerInfo = dynamicMapper.getReviewerInfo(n.getPid(),0);
            } else {
                reviewerInfo = dynamicMapper.getReviewerInfo(n.getDynamicId(),1);
            }
            if (null != reviewerInfo && !reviewerInfo.isEmpty()){
                n.setpUserId((Long)reviewerInfo.get("pUserId"));
                n.setpUserName((String)reviewerInfo.get("pUserName"));
                n.setpUserPic((String)reviewerInfo.get("pUserPic"));
            }
        });
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
           return JSONObject.parseObject(obj.toString(),LocationVO.class);
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

    /**
     * 删除动态相关信息
     * @param dynamicId
     * @return
     */
    public R deleteDynamic(Long dynamicId){
        try {
            dynamicMapper.deleteDynamic(dynamicId);
            return R.ok();
        }catch (Exception e){
            return R.error("操作失败");
        }
    }

    public IndexDynamicListDTO selectByDynamicId(Long userId,Long dynamicId){
        try {
            IndexDynamicListDTO dynamic = dynamicMapper.selectByDynamicId(userId,dynamicId);
            if (null != dynamic){
                String pics = dynamic.getPics();
                if  (StringUtils.isNotBlank(pics)){
                    dynamic.setDynamicPics(Arrays.asList(pics.split(",")));
                    dynamic.setPics(null);
                }
                List<UserDynamicCommentListDTO> commentDetailList = dynamic.getCommentDetailList();
                setReviewerInfo(commentDetailList);
            }
            return dynamic;
        }catch (Exception e){
            return null;
        }
    }
}
