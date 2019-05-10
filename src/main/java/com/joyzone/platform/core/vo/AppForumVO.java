package com.joyzone.platform.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Description:TODO
 *
 * @author Mr.Gx
 * date: 2019/5/4
 */
@ApiModel("App获取论坛信息")
public class AppForumVO {

    @ApiModelProperty("用户名")
    private Long id;

    @ApiModelProperty("用户名")
    private String content;

    @ApiModelProperty("类型 0 建议 1吐槽")
    private Integer type;

    @ApiModelProperty("点赞数")
    private Integer pointNum;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("头像")
    private String headPic;

    @ApiModelProperty("性别 0 男 1女")
    private Integer sex;

    @ApiModelProperty("学历")
    private String education;

    @ApiModelProperty("职业")
    private String profession;

    @ApiModelProperty("年龄")
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("年龄")
    private Date createTime;

    @ApiModelProperty("跟帖人数")
    private List<AppForumVO> forumDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPointNum() {
        return pointNum;
    }

    public void setPointNum(Integer pointNum) {
        this.pointNum = pointNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<AppForumVO> getForumDetails() {
        return forumDetails;
    }

    public void setForumDetails(List<AppForumVO> forumDetails) {
        this.forumDetails = forumDetails;
    }
}
