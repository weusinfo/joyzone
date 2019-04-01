package com.joyzone.platform.core.model;

import javax.persistence.*;

@Table(name = "menu")
public class MenuModel {
    @Id
    private Long id;

    /**
     * 菜单名字
     */
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    private String description;

    private String url;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取菜单名字
     *
     * @return name - 菜单名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单名字
     *
     * @param name 菜单名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}