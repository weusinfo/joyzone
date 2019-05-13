package com.joyzone.platform.core.vo;

import com.joyzone.platform.core.dto.Menus;
import com.joyzone.platform.core.model.SysUserModel;

import java.util.List;

/**
 * Description:TODO
 * @author Mr.Gx
 * date: 2019/5/12
 */
public class SysUserVO {

    private SysUserModel user;

    private List<Menus> menuList;

    public SysUserModel getUser() {
        return user;
    }

    public void setUser(SysUserModel user) {
        this.user = user;
    }

    public List<Menus> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menus> menuList) {
        this.menuList = menuList;
    }
}
