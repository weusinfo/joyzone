package com.joyzone.platform.core.mapper;

import com.joyzone.platform.core.dto.SystemHobbyDTO;
import com.joyzone.platform.core.model.HobbyModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface HobbyMapper extends Mapper<HobbyModel>{
    List<SystemHobbyDTO> getSystemHobbyList(@Param("userId") Long userId);
    List<SystemHobbyDTO> getHobbyNamesByType(@Param("hobbyType") String hobbyType,@Param("userId")Long userId);

}
