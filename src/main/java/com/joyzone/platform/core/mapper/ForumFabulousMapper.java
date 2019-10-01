package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ForumFabulous;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ForumFabulousMapper extends Mapper<ForumFabulous> {

    ForumFabulous findByUserForum(@Param("userId") Long userId, @Param("forumId") Long forumId);

    ForumFabulous findByUserForumDetail(Long userId,Long forumDetailId);
}