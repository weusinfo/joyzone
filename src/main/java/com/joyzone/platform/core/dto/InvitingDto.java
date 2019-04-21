package com.joyzone.platform.core.dto;


import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("受邀函前端交互")
public class InvitingDto extends BaseModel {
    //用户ID
    @ApiModelProperty("用户ID")
    private Long userId;
    //用户头像
    @ApiModelProperty("用户头像")
    private String headPic;
    //地址
    @ApiModelProperty("地址")
    private String address;
    //主题
    @ApiModelProperty("主题")
    private String content;
    //玩耍时间
    @ApiModelProperty("玩耍时间")
    private Date startTime;
    //支付类型名称
    @ApiModelProperty("支付类型名称")
    private String payWayName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }
}
