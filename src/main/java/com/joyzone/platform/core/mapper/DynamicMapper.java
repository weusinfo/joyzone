package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.IndexDynamicListDTO;
import com.joyzone.platform.core.dto.UserDynamicCommentListDTO;
import com.joyzone.platform.core.dto.UserDynamicDTO;
import com.joyzone.platform.core.dto.UserDynamicListDto;
import com.joyzone.platform.core.model.DynamicModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DynamicMapper extends Mapper<DynamicModel> {

    UserDynamicDTO selectByUserDynamic(@Param("userId") Long userId);

    List<UserDynamicListDto> queryUserDynamicList(@Param("userId") Long userId,@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    List<UserDynamicCommentListDTO> queryUserDynamicCommentList(@Param("dynamicId") Long dynamicId);

    List<IndexDynamicListDTO> getIndexDynamicList(@Param("userId") Long userId, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

}