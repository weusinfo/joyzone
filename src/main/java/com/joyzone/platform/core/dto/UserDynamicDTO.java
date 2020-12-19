package com.joyzone.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;
import java.util.List;


@Configuration
@ApiModel("我的-动态/个人主页返回DTO")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class UserDynamicDTO extends BaseModel{

    public UserDynamicDTO(){

    }

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户头像")
    private String userPic;

    @ApiModelProperty("用户昵称")
    private String userName;

    @ApiModelProperty("用户性别")
    private Integer userSex;

    @ApiModelProperty("用户年龄")
    private Integer userAge;

    @ApiModelProperty("是否关注了该用户")
    private Integer followed;

    @ApiModelProperty("动态列表")
    private List<UserDynamicListDto> userDynamicList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getFollowed() {
        return followed;
    }

    public void setFollowed(Integer followed) {
        this.followed = followed;
    }

    public List<UserDynamicListDto> getUserDynamicList() {
        return userDynamicList;
    }

    public void setUserDynamicList(List<UserDynamicListDto> userDynamicList) {
        this.userDynamicList = userDynamicList;
    }
}
