package com.joyzone.platform.core.model;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Description:TODO
 *
 * @author Mr.Gx
 * date: 2019/3/29
 */
public class BaseModel implements Serializable {

    public static final int PAGE_NUM = 1;   //页数
    public static final int PAGE_SIZE = 10;//条数
    public static final int STATUS_SUCCESS = 1; //正常
    public static final int STATUS_FAILL = 0;   //失效

    @Transient
    private Integer pageNum;

    @Transient
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
