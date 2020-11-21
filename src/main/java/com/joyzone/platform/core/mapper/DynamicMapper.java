package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.IndexDynamicListDTO;
import com.joyzone.platform.core.dto.UserDynamicCommentListDto;
import com.joyzone.platform.core.dto.UserDynamicDto;
import com.joyzone.platform.core.dto.UserDynamicListDto;
import com.joyzone.platform.core.model.DynamicModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DynamicMapper extends Mapper<DynamicModel> {

    List<UserDynamicDto> getUserDynamicList(@Param("userId") Long userId);

    List<UserDynamicListDto> queryUserDynamicList(@Param("userId") Long userId);

    List<UserDynamicCommentListDto> queryUserDynamicCommentList(@Param("dynamicId") Long dynamicId);

    List<IndexDynamicListDTO> getIndexDynamicList(@Param("userId") Long userId, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

}