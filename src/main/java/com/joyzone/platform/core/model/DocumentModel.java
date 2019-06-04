package com.joyzone.platform.core.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = DocumentModel.TABLE_NAME)
@ApiModel("文件资源")
public class DocumentModel extends BaseModel{

    protected static final String TABLE_NAME = "document";

    public DocumentModel(){}

    public DocumentModel(String filePath){
        this.filePath = filePath;
    }
    /**
     * 自增长主键
     */
    @Id
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 文件拥有者，关联user.id
     */
    @ApiModelProperty("文件拥有者，关联user.id")
    private Long owner;

    /**
     * 文件名
     */
    @Column(name = "file_name")
    @ApiModelProperty("文件名")
    private String fileName;

    /**
     * 文件路径
     */
    @Column(name = "file_path")
    @ApiModelProperty("")
    private String filePath;

    /**
     * 文件类型,0:图片（一般是图片，有其它类型再定义）
     */
    @ApiModelProperty("文件类型,0:图片")
    private Boolean type;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;

    /**
     * 获取自增长主键
     *
     * @return id - 自增长主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增长主键
     *
     * @param id 自增长主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取文件拥有者，关联user.id
     *
     * @return owner - 文件拥有者，关联user.id
     */
    public Long getOwner() {
        return owner;
    }

    /**
     * 设置文件拥有者，关联user.id
     *
     * @param owner 文件拥有者，关联user.id
     */
    public void setOwner(Long owner) {
        this.owner = owner;
    }

    /**
     * 获取文件名
     *
     * @return file_name - 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件路径
     *
     * @return file_path - 文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置文件路径
     *
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取文件类型,0:图片（一般是图片，有其它类型再定义）
     *
     * @return type - 文件类型,0:图片（一般是图片，有其它类型再定义）
     */
    public Boolean getType() {
        return type;
    }

    /**
     * 设置文件类型,0:图片（一般是图片，有其它类型再定义）
     *
     * @param type 文件类型,0:图片（一般是图片，有其它类型再定义）
     */
    public void setType(Boolean type) {
        this.type = type;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}