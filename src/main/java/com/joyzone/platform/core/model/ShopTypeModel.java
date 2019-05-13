package com.joyzone.platform.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = ShopTypeModel.TABLE_NAME)
@ApiModel("店家种类信息")
public class ShopTypeModel extends BaseModel{

    protected static final String TABLE_NAME = "shop_type";
    public static final int SHOP_TYPE_ZD = 0;//组队类型
    public static final int SHOP_TYPE_TYJ = 1;//体验券类型

    @Id
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 类型名或种类名称
     */
    @ApiModelProperty("类型名或种类名称")
    private String name;

    /**
     * 0 : 组队类型   1：体验类型
     */
    @ApiModelProperty("0 : 组队类型   1：体验类型")
    private Integer type;

    @ApiModelProperty("父级ID")
    private Long pid;

    /**
     *  0 失效  1 正常
     */
    @ApiModelProperty("0 失效  1 正常")
    private Integer status;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

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
     * 获取类型名或种类名称
     *
     * @return name - 类型名或种类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类型名或种类名称
     *
     * @param name 类型名或种类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取1 : 组队类型   2：体验类型
     *
     * @return type - 1 : 组队类型   2：体验类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1 : 组队类型   2：体验类型
     *
     * @param type 1 : 组队类型   2：体验类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return pid
     */
    public Long getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取 0 失效  1 正常
     *
     * @return status -  0 失效  1 正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 0 失效  1 正常
     *
     * @param status  0 失效  1 正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}