package com.joyzone.platform.core.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 用户兴趣标签
 * @author louishe
 *
 */
@Table(name = "tags")
@ApiModel(description="用户兴趣标签")
public class TagsModel {
	
	
	@Id
    @Excel(name = "帖子标识")
    @ApiModelProperty("主键")
	private Long id;

    @Excel(name = "帖子标识")
    @ApiModelProperty("标签名称")
	private String name;
    
    @Excel(name = "用户ID")
    @ApiModelProperty("用户ID")
    private Long userId;
    
    @Excel(name = "兴趣分类")
    @ApiModelProperty("兴趣分类")
    private String type;
    
    @Excel(name = "创建时间")
    @ApiModelProperty("创建时间")
    private Date createTime;
    
    @Excel(name = "标签引用次数")
    @ApiModelProperty("标签引用次数")
    private Long num;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	
	

}
