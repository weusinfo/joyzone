package com.joyzone.platform.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = UserModel.TABLE_NAME)
public class UserModel extends BaseModel{

    protected static final String TABLE_NAME = "user";

    /**
     * 自增长主键
     */
    @Id
    private Long id;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型
     */
    private Boolean type;

    /**
     * 用户电话号码
     */
    private String phone;

    /**
     * 头像
     */
    @Column(name = "head_pic")
    private String headPic;

    /**
     * 性别
     */
    private Boolean sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 用户状态
     */
    private Boolean status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取用户类型
     *
     * @return type - 用户类型
     */
    public Boolean getType() {
        return type;
    }

    /**
     * 设置用户类型
     *
     * @param type 用户类型
     */
    public void setType(Boolean type) {
        this.type = type;
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
     * 获取性别
     *
     * @return sex - 性别
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
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
     * 获取用户状态
     *
     * @return status - 用户状态
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置用户状态
     *
     * @param status 用户状态
     */
    public void setStatus(Boolean status) {
        this.status = status;
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
}