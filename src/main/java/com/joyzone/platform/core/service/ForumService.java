package com.joyzone.platform.core.service;


import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.common.utils.SensitiveWordUtils;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.ForumDetailMapper;
import com.joyzone.platform.core.mapper.ForumFabulousMapper;
import com.joyzone.platform.core.mapper.ForumMapper;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.model.ForumDetailModel;
import com.joyzone.platform.core.model.ForumFabulous;
import com.joyzone.platform.core.model.ForumModel;
import com.joyzone.platform.core.vo.AppForumVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ForumService extends BaseService<ForumModel> {

    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private ForumDetailMapper forumDetailMapper;
    @Autowired
    private ForumFabulousMapper forumFabulousMapper;


    /**
     * 获取论坛清单
     * @param forumModel
     * Mr.Gx
     */
    public List<ForumModel> getForumList(ForumModel forumModel){
       List<ForumModel> list = forumMapper.getForumList(forumModel);
       if(list != null && list.size() > 0){
           for(ForumModel model : list){
             List<ForumDetailModel>  forumDetails = forumDetailMapper.findByForumId(model.getId());
             model.setForumNum(forumDetails.size());
           }
       }
        return list;
    }

    /**
     * 添加及更新论坛信息
     * @param forumModel
     * @return
     */
    public R saveForumNum(ForumModel forumModel){
        if(forumModel == null)
            return R.error(R.STATUS_FAIL,"参数不能为空.");
        if(forumModel.getUserId() == null)
            return R.error(R.STATUS_FAIL,"发帖人ID不能为空.");
        /*if(forumModel.getType() == null)
            return R.error(R.STATUS_FAIL,"发帖类型不能为空.");*/
        String content = forumModel.getContent();
        if(StringUtils.isBlank(content))
            return R.error(R.STATUS_FAIL,"发帖内容不能为空.");
        //TODO 进行铭感字处理为:**
        //forumModel.setContent(SensitiveWordUtils.getInstance().filterInfo(content));
        Date date = new Date();
        if(forumModel.getId() == null){
           forumModel.setCreateTime(date);
           return forumMapper.insertSelective(forumModel) > 0 ?
                   R.ok("添加成功.") : R.error(R.STATUS_FAIL,"添加失败.");
        }
        forumModel.setUpdateTime(date);
        return forumMapper.updateByPrimaryKeySelective(forumModel) > 0 ?
                R.ok("更新成功.") : R.error(R.STATUS_FAIL,"更新失败.");
    }

    /**
     * 论坛添加跟帖信息
     * @param forumDetailModel
     * @return
     */
    public R saveForumDetail(ForumDetailModel forumDetailModel){
        if(forumDetailModel == null)
            return R.error(R.STATUS_FAIL,"参数不能为空.");
        if(forumDetailModel.getUserId() == null)
            return R.error(R.STATUS_FAIL,"发帖人ID不能为空.");
        if(forumDetailModel.getForumId() == null)
            return R.error(R.STATUS_FAIL,"主帖ID不能为空.");
        String content = forumDetailModel.getContent();
        if(StringUtils.isBlank(content))
            return R.error(R.STATUS_FAIL,"发帖内容不能为空.");
        //TODO 进行铭感字处理为:**
        //forumDetailModel.setContent(SensitiveWordUtils.getInstance().filterInfo(content));
        Date date = new Date();
        if(forumDetailModel.getId() == null){
            forumDetailModel.setCreateTime(date);
            return forumDetailMapper.insertSelective(forumDetailModel) > 0 ?
                    R.ok("添加成功.") : R.error(R.STATUS_FAIL,"添加失败.");
        }
        forumDetailModel.setUpdateTime(date);
        return forumDetailMapper.updateByPrimaryKeySelective(forumDetailModel) > 0 ?
                R.ok("更新成功.") : R.error(R.STATUS_FAIL,"更新失败.");
    }


    /**
     * App我的论坛列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public R getAppForumList(Long userId,Integer pageNum,Integer pageSize){
        List<AppForumVO> list =  forumMapper.getAppForumList(userId,pageNum,pageSize);
        if(list != null && list.size() > 0){
            /*for(AppForumVO appForumVO : list){
                ForumFabulous forumFabulous = forumFabulousMapper.findByUserForum(userId,appForumVO.getId());
                Boolean status = false;
                if(forumFabulous != null){
                    status = true;
                }
                appForumVO.setUserIsPoint(status);
                appForumVO.setForumDetails(getForumDetails(userId,appForumVO.getId()));
            }*/
            Page page = new Page();
            page=(Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,list);
    }

    /**
     * 获取论坛跟帖明细
     * @param userId
     * @param forumId
     * @return
     */
    private List<AppForumVO> getForumDetails(Long userId,Long forumId){
        List<AppForumVO> list = forumDetailMapper.selectForumDetails(forumId);
        if(list != null && list.size() > 0) {
            for (AppForumVO appForumVO : list) {
                ForumFabulous forumFabulous = forumFabulousMapper.findByUserForumDetail(userId, appForumVO.getId());
                Integer status = 0;
                if (forumFabulous != null) {
                    status = 1;
                }
                appForumVO.setUserIsPoint(status);
            }
        }
        return list;
    }

    /**
     * 对主评论进行点赞
     * @param userId
     * @return
     */
    public R updateForumPointNum(Long userId,Long forumId,Integer type){
        ForumFabulous forumFabulous = forumFabulousMapper.findByUserForum(userId,forumId);
        if(type == 1){//点赞
            if(forumFabulous != null){
                return R.error("您已经点过赞了");
            }
            forumFabulous = new ForumFabulous();
            forumFabulous.setUserId(userId);
            forumFabulous.setForumId(forumId);
            forumFabulous.setType(ForumFabulous.FORUM_TYPE_ZT);
            forumFabulous.setCreateTime(new Date());
            int ret = forumFabulousMapper.insertSelective(forumFabulous);
            if(ret > 0){
                forumMapper.updateForumPointNum(forumId,type);
                return R.ok();
            }
        }
        if(type == 2){//取消
            if(forumFabulous != null){
                int ret =  forumFabulousMapper.deleteByPrimaryKey(forumFabulous.getId());
                if(ret > 0){
                    forumMapper.updateForumPointNum(forumId,type);
                    return R.ok();
                }
            }
        }
        return R.error("操作失败");
    }

    /**
     * 对跟帖评论进行点赞
     * @param userId
     * @return
     */
    public R updateForumDetailPointNum(Long userId,Long forumDetailId,Integer type){
        ForumFabulous forumFabulous = forumFabulousMapper.findByUserForumDetail(userId,forumDetailId);
        if(type == 1){
            if(forumFabulous != null){
                return R.error("您已经点过赞了");
            }
            forumFabulous = new ForumFabulous();
            forumFabulous.setUserId(userId);
            forumFabulous.setForumDetailId(forumDetailId);
            forumFabulous.setType(ForumFabulous.FORUM_TYPE_GT);
            forumFabulous.setCreateTime(new Date());
            int ret = forumFabulousMapper.insertSelective(forumFabulous);
            if(ret > 0){
                forumDetailMapper.updateForumDetailPointNum(forumDetailId,type);
                return R.ok();
            }
        }
        if(type == 2){
            if(forumFabulous != null){
               int ret = forumFabulousMapper.deleteByPrimaryKey(forumFabulous.getId());
               if(ret > 0){
                   forumDetailMapper.updateForumDetailPointNum(forumDetailId,type);
                   return R.ok();
               }
            }
        }
        return  R.error("操作失败");
    }
}
