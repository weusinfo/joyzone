package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.model.ForumDetailModel;
import com.joyzone.platform.core.vo.AppForumVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ForumDetailMapper extends Mapper<ForumDetailModel> {

    List<ForumDetailModel> findByForumId(@Param("forumId") Long forumId);

    List<AppForumVO> selectForumDetails(@Param("forumId") Long forumId);

    int updateForumDetailPointNum(@Param("id") Long id,@Param("type") Integer type);
}