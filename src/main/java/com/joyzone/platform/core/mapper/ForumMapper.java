package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ForumModel;
import com.joyzone.platform.core.vo.AppForumVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ForumMapper extends Mapper<ForumModel> {

    List<ForumModel> getForumList(ForumModel forumModel);

    List<AppForumVO> getAppForumList(@Param("userId") Long userId,@Param("pageNum") Integer pageNum,
                                     @Param("pageSize") Integer pageSize);

    int updateForumPointNum(@Param("id") Long id, @Param("type") Integer type);
}