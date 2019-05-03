package com.joyzone.platform.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Table(name = "menu")
@ApiModel("菜单信息")
public class MenuModel  extends BaseModel{
    @Id
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 菜单名字
     */
    @ApiModelProperty("菜单名字")
    private String name;

    @Column(name = "parent_id")
    @ApiModelProperty("父级菜单ID")
    private Long parentId;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("访问地址")
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