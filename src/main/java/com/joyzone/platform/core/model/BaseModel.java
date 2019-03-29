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

    @Transient
    private Integer pageNum;

    @Transient
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
