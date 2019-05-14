package com.joyzone.platform.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Description:TODO
 *
 * @author Mr.Gx
 * date: 2019/3/29
 */
@ApiModel("共享类")
public class BaseModel implements Serializable {

    public static final int PAGE_NUM = 1;   //页数
    public static final int PAGE_SIZE = 10;//条数
    public static final int STATUS_SUCCESS = 1; //正常
    public static final int STATUS_FAILL = 0;   //失效
    public static final int PAGE_SIZE_SIX = 6;   //默认

    @Transient
    @ApiModelProperty("页数")
    private Integer pageNum;

    @Transient
    @ApiModelProperty("每页条数")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum == null ? PAGE_NUM : pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? PAGE_SIZE : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
