package com.joyzone.platform.core.service;


import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.common.utils.SensitiveWordUtils;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.ForumDetailMapper;
import com.joyzone.platform.core.mapper.ForumMapper;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.model.ForumDetailModel;
import com.joyzone.platform.core.model.ForumModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ForumService extends BaseService<ForumModel> {

    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private ForumDetailMapper forumDetailMapper;


    /**
     * 获取论坛清单
     * @param forumModel
     * Mr.Gx
     */
    public List<ForumModel> getForumList(ForumModel forumModel){
        if(forumModel.getPageNum() == null)
            forumModel.setPageNum(BaseModel.PAGE_NUM);
        if(forumModel.getPageSize() == null)
            forumModel.setPageSize(BaseModel.PAGE_SIZE);

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
        if(forumModel.getType() == null)
            return R.error(R.STATUS_FAIL,"发帖类型不能为空.");
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
}
