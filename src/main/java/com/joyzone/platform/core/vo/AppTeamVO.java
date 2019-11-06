package com.joyzone.platform.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

/**
 * Description:TODO
 * @author Mr.Gx
 * date: 2019/5/4
 */
@ApiModel("APP获取组队相关信息")
public class AppTeamVO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("组队店家种类名称")
    private String shopTypeName;

    @ApiModelProperty("玩耍开始时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty("组队加入成员头像")
    private List<String> userPic;

    @ApiModelProperty("当前用户是否加入状态0 否,1 已加入")
    private Integer userJoinStatus;

    @ApiModelProperty("发起人的名称")
    private String userName;

    @ApiModelProperty("发起人的头像")
    private String headPic;

    @ApiModelProperty("发起人的性别：0男,1女")
    private Integer sex;

    @ApiModelProperty("发起人的年龄")
    private Integer age;

    @ApiModelProperty("发起人的职业")
    private String profession;

    @ApiModelProperty("发起人的学历")
    private String education;

    @ApiModelProperty("所属店家名称")
    private String shopName;

    @ApiModelProperty("所属店家的背景图")
    private String coverImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopTypeName() {
        return shopTypeName;
    }

    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public List<String> getUserPic() {
        return userPic;
    }

    public void setUserPic(List<String> userPic) {
        this.userPic = userPic;
    }

    public Integer getUserJoinStatus() {
        return userJoinStatus;
    }

    public void setUserJoinStatus(Integer userJoinStatus) {
        this.userJoinStatus = userJoinStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
}
