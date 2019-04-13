package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ForumDetailModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ForumDetailMapper extends Mapper<ForumDetailModel> {

    List<ForumDetailModel> findByForumId(@Param("forumId") Long forumId);
}