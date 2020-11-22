package com.joyzone.platform.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel("App评论列表")
public class IndexShopCommentVO {

    @ApiModelProperty("商家评论统计")
    private ShopCommentCountVO commentCount;


    @ApiModelProperty("商家评论列表")
    List<ShopCommentVO> comments;


    public ShopCommentCountVO getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(ShopCommentCountVO commentCount) {
        this.commentCount = commentCount;
    }

    public List<ShopCommentVO> getComments() {
        return comments;
    }

    public void setComments(List<ShopCommentVO> comments) {
        this.comments = comments;
    }
}
