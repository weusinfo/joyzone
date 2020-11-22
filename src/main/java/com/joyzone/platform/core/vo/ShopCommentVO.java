package com.joyzone.platform.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel("App评论列表")
public class ShopCommentVO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("评论者id")
    private Long userId;

    @ApiModelProperty("商家id")
    private Long shopId;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("类型")
    private Byte type;

    @ApiModelProperty("是否匿名")
    private Boolean isAnonym;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论相片地址")
    private List<String> picUrls;

    @ApiModelProperty("商家回复")
    private String adminContent;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("用户年龄")
    private Integer age;

    @ApiModelProperty("头像")
    private String headPic;

    @ApiModelProperty("性别")
    private Integer sex;

    private String pics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getIsAnonym() {
        return isAnonym;
    }

    public void setIsAnonym(Boolean isAnonym) {
        this.isAnonym = isAnonym;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

    public String getAdminContent() {
        return adminContent;
    }

    public void setAdminContent(String adminContent) {
        this.adminContent = adminContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }
}
