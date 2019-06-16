package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ForumFabulous;
import tk.mybatis.mapper.common.Mapper;

public interface ForumFabulousMapper extends Mapper<ForumFabulous> {

    ForumFabulous findByUserForum(Long userId,Long forumId);

    ForumFabulous findByUserForumDetail(Long userId,Long forumDetailId);
}