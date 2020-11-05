package com.joyzone.platform.core.service;

import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.mapper.DynamicCommentMapper;
import com.joyzone.platform.core.model.DynamicCommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

/**
 * Description:TODO
 *
 * @author zhangyu
 * date: 2020/11/05
 */
@Service
@Transactional
public class DynamicCommentSerivce extends BaseService<DynamicCommentModel> {

    @Autowired
    private DynamicCommentMapper dynamicCommentMapper;

    /**
     * 评论动态
     * @param dynamicCommentModel dynamicCommentModel
     * zhangyu
     */
    public int saveDynamicComment(DynamicCommentModel dynamicCommentModel){
        dynamicCommentModel.setCreateTime(new Date());
        dynamicCommentModel.setUpdateTime(new Date());
        return dynamicCommentMapper.insert(dynamicCommentModel);
    }

}
