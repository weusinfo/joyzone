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
@Table(name = "hobby")
@ApiModel(description="系统爱好表")
public class HobbyModel {
	
	
	@Id
    @Excel(name = "爱好主键")
    @ApiModelProperty("主键")
	private Long id;

    @Excel(name = "帖子标识")
    @ApiModelProperty("标签名称")
	private String hobbyName;
    
    @Excel(name = "爱好状态")
    @ApiModelProperty("用户ID")
    private Integer status;
    
    @Excel(name = "爱好分类")
    @ApiModelProperty("爱好分类")
    private String type;
    
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

	public String getHobbyName() {
		return hobbyName;
	}

	public void setHobbyName(String hobbyName) {
		this.hobbyName = hobbyName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
