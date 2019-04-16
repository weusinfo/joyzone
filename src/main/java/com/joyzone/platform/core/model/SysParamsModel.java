package com.joyzone.platform.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_params")
public class SysParamsModel {
    @Id
    private Integer id;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "param_value")
    private String paramValue;

    /**
     * 备注
     */
    private String remark;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效;0:有效；1：无效
     */
    private Integer status;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return param_name
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * @param paramName
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * @return param_value
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * @param paramValue
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * 获取是否有效;0:有效；1：无效
     *
     * @return status - 是否有效;0:有效；1：无效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否有效;0:有效；1：无效
     *
     * @param status 是否有效;0:有效；1：无效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}