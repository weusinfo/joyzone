package com.joyzone.platform.core.model;

import javax.persistence.*;

@Table(name = "company_message")
public class CompanyMessage {
    @Id
    private Long id;

    /**
     * 称呼
     */
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 留言内容
     */
    private String content;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取称呼
     *
     * @return name - 称呼
     */
    public String getName() {
        return name;
    }

    /**
     * 设置称呼
     *
     * @param name 称呼
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取电话号码
     *
     * @return phone - 电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话号码
     *
     * @param phone 电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取留言内容
     *
     * @return content - 留言内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置留言内容
     *
     * @param content 留言内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}