package com.joyzone.platform.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
@ApiModel("系统用户信息")
public class SysUserModel  extends BaseModel{
    @Id
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    /**
     * 0:男，1：女
     */
    @ApiModelProperty("0:男，1：女")
    private Integer sex;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    @Column(name = "head_img")
    private String headImg;

    @ApiModelProperty("电话号码")
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 用户状态；0：激活，1：禁用
     */
    @ApiModelProperty("用户状态；0：激活，1：禁用")
    private Integer status;

    /**
     * 门店编号
     */
    @ApiModelProperty("所属门店ID")
    @Column(name = "shop_id")
    private Long shopId;
    
    /**
     * 上次登陆时间
     */
    @ApiModelProperty("上次登陆时间 yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "last_login_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    /**
     * 登陆出错次数
     */
    @ApiModelProperty("登陆出错次数")
    @Column(name = "try_err_times")
	private Integer tryErrTimes;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取0:男，1：女
     *
     * @return sex - 0:男，1：女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置0:男，1：女
     *
     * @param sex 0:男，1：女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取头像
     *
     * @return head_img - 头像
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * 设置头像
     *
     * @param headImg 头像
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
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

    /**
     * 获取用户状态；0：激活，1：禁用
     *
     * @return status - 用户状态；0：激活，1：禁用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置用户状态；0：激活，1：禁用
     *
     * @param status 用户状态；0：激活，1：禁用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取门店编号
     *
     * @return shop_id - 门店编号
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置门店编号
     *
     * @param shopId 门店编号
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
    public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getTryErrTimes() {
		return tryErrTimes;
	}

	public void setTryErrTimes(Integer tryErrTimes) {
		this.tryErrTimes = tryErrTimes;
	}
}