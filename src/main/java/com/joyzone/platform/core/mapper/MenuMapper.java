package com.joyzone.platform.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.joyzone.platform.core.model.MenuModel;
import tk.mybatis.mapper.common.Mapper;

public interface MenuMapper extends Mapper<MenuModel> {
	
	List<MenuModel> getMenus(@Param("userId") Long userId);
}