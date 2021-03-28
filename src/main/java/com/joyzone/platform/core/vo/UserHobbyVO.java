package com.joyzone.platform.core.vo;

import com.joyzone.platform.core.dto.Menus;
import com.joyzone.platform.core.model.SysUserModel;

import java.util.List;

/**
 * Description:TODO
 * @author Mr.Gx
 * date: 2019/5/12
 */
public class UserHobbyVO {

    private Long userId;

    private List<Long> hobbyIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getHobbyIds() {
        return hobbyIds;
    }

    public void setHobbyIds(List<Long> hobbyIds) {
        this.hobbyIds = hobbyIds;
    }
}
