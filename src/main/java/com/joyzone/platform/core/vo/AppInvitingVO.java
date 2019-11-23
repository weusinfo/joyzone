package com.joyzone.platform.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.common.utils.DateUtils;
import com.joyzone.platform.core.dto.UserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

/**
 * Description:TODO
 * @author Mr.Gx
 * date: 2019/4/21
 */
@ApiModel("App输出数据信息VO")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class AppInvitingVO {

    @ApiModelProperty("邀约ID")
    private Long invitingId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("用户头像")
    private String headPic;

    @ApiModelProperty("用户年龄")
    private Integer userAge;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("主题")
    private String content;

    @ApiModelProperty("玩耍时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty("支付类型名称")
    private Integer payWay;

    @ApiModelProperty("邀约类型")
    private Integer inviteType;

    @ApiModelProperty("4个tab页对应的值：1:受邀列表 2：正式函 3：我的邀请 4：回函")
    private Integer type;

    @ApiModelProperty("组队人数")
    private Integer personNum;

    @ApiModelProperty("第一个tab列表，受邀列表，0未同意 1已同意")
    private Integer agreeOrNot;

    @ApiModelProperty("第四个tab列表，我的回函，0未邀请 1已邀请")
    private Integer inviteOrNot;

    @ApiModelProperty("参与邀请的人员集合")
    private List<UserDto> userDtoList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getInviteType() {
        return inviteType;
    }

    public void setInviteType(Integer inviteType) {
        this.inviteType = inviteType;
    }

    public Long getInvitingId() {
        return invitingId;
    }

    public void setInvitingId(Long invitingId) {
        this.invitingId = invitingId;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public Integer getAgreeOrNot() {
        return agreeOrNot;
    }

    public void setAgreeOrNot(Integer agreeOrNot) {
        this.agreeOrNot = agreeOrNot;
    }

    public Integer getInviteOrNot() {
        return inviteOrNot;
    }

    public void setInviteOrNot(Integer inviteOrNot) {
        this.inviteOrNot = inviteOrNot;
    }

    public List<UserDto> getUserDtoList() {
        return userDtoList;
    }

    public void setUserDtoList(List<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }
}
