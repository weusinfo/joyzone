package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ForumModel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ForumMapper extends Mapper<ForumModel> {

    List<ForumModel> getForumList(ForumModel forumModel);
}