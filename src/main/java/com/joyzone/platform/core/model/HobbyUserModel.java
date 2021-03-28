package com.joyzone.platform.core.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * 系统爱好表
 * @author zhangyu
 *
 */
@Table(name = "hobby_user")
@ApiModel(description="用户爱好表")
public class HobbyUserModel {
	
	
	@Id
    @Excel(name = "用户爱好主键")
    @ApiModelProperty("主键")
	private Long id;

    @Excel(name = "用户id")
    @ApiModelProperty("用户id")
	private Long userId;
    
    @Excel(name = "爱好id")
    @ApiModelProperty("爱好id")
    private Long hobbyId;

	@Excel(name = "自定义爱好")
	@ApiModelProperty("自定义爱好")
	private String hobbyOwn;

    @Excel(name = "创建时间")
    @ApiModelProperty("创建时间")
    private Date createTime;

	@Excel(name = "更新时间")
	@ApiModelProperty("更新时间")
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getHobbyId() {
		return hobbyId;
	}

	public void setHobbyId(Long hobbyId) {
		this.hobbyId = hobbyId;
	}

	public String getHobbyOwn() {
		return hobbyOwn;
	}

	public void setHobbyOwn(String hobbyOwn) {
		this.hobbyOwn = hobbyOwn;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
