package com.joyzone.platform.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = UserModel.TABLE_NAME)
@ApiModel("APP用户信息")
public class UserModel extends BaseModel{

    protected static final String TABLE_NAME = "user";

    //密码加盐规则
    public static final String PASSWORD_RULE = "joy_zone";


    public UserModel(String phone) {
        this.phone = phone;
    }
    public UserModel() {
    }
    /**
     * 自增长主键
     */
    @Id
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    @Column(name = "user_name")
    private String userName;

    @Column(name="login_name")
    @ApiModelProperty("用户登录账号")
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty("用户登录密码")
    private String password;

    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型")
    private Integer type;

    /**
     * 用户电话号码
     */
    @ApiModelProperty("用户电话")
    private String phone;

    /**
     * 头像
     */
    @ApiModelProperty("用户头像")
    @Column(name = "head_pic")
    private String headPic;

    /**
     * 性别
     */
    @ApiModelProperty("性别 0 男 1女")
    private Integer sex;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private Date birthday;

    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private Integer status;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 职业
     */
    @ApiModelProperty("职业")
    private String profession;

    /**
     * 学历
     */
    @ApiModelProperty("学历")
    private String education;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 获取自增长主键
     *
     * @return id - 自增长主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增长主键
     *
     * @param id 自增长主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户姓名
     *
     * @return user_name - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户电话号码
     *
     * @return phone - 用户电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置用户电话号码
     *
     * @param phone 用户电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取头像
     *
     * @return head_pic - 头像
     */
    public String getHeadPic() {
        return headPic;
    }

    /**
     * 设置头像
     *
     * @param headPic 头像
     */
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}