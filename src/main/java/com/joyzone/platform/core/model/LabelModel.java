package com.joyzone.platform.core.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 攻略标签
 * @author louishe
 *
 */
@ApiModel("攻略标签")
@Table(name="label")
public class LabelModel {
	
	@Id
    @Excel(name = "攻略标签ID")
    @ApiModelProperty("攻略标签ID")
	private Long id;
	
	@Excel(name = "攻略标签类型")
    @ApiModelProperty("攻略标签类型")
	private String type;
	
	@Excel(name = "攻略标签名称")
    @ApiModelProperty("攻略标签名称")
	private String name;
	
	@Excel(name = "攻略标签创建人")
    @ApiModelProperty("攻略标签创建人")
	private Long userId;
	
	@Excel(name = "攻略标签创建时间")
    @ApiModelProperty("攻略标签创建时间")
	private Date createTime;
	
	@Excel(name = "攻略标签引用次数")
    @ApiModelProperty("攻略标签引用次数")
	private Long num;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
