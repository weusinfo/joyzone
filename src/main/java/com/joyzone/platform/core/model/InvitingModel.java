package com.joyzone.platform.core.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.joyzone.platform.common.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = InvitingModel.TABLE_NAME)
@ApiModel("受邀列表")
public class InvitingModel extends BaseModel{

    protected static final String TABLE_NAME = "inviting";

    @Id
    @Excel(name="邀请函标识")
    @ApiModelProperty("邀请ID")
    private Long id;

    @ApiModelProperty("发起人")
    private Long owner;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 主题
     */
    @ApiModelProperty("主题")
    private String content;

    /**
     * 邀约类型
     */
    @ApiModelProperty("邀请函标识")
    @Excel(name="邀请函标识",replace = {"默认_0"})
    private Integer type;

    /**
     * 受邀人数
     */
    @Excel(name="受邀人数")
    @Transient
    private Integer invitingNum;

    /**
     * 邀约状态;0:有效；1：失效
     */
    @ApiModelProperty("邀约状态;0:有效；1：失效")
    private Integer status;

    /**
     * 支付方式：0：AA，1：女生免费；2：赢家免费
     */
    @ApiModelProperty("付方式：0：AA，1：邀约者请客 2：女生免费；3：赢家免费 4:女生半价 5：赢家半价")
    @Column(name = "pay_way")
    @Excel(name="支付类型",replace = {"AA_0","邀约者请客_1","女生免费_2","赢家免费_3","女生半价_4","赢家半价_5"})
    private Integer payWay;

    /**
     * 邀约成功状态：0：失败；1：成功
     */
    @Excel(name="结果",replace = {"失败_0","成功_1"})
    @ApiModelProperty("邀约成功状态：0：失败；1：成功")
    private Integer result;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    @Excel(name="体验时间",exportFormat = "yyyy-MM-dd HH")
    private Date startTime;

    /**
     * 参加人数限制
     */
    @Excel(name="限制人数")
    @ApiModelProperty("限制人数")
    private Integer number;

    /**
     * 邀约创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    @Excel(name="创建时间",exportFormat = "yyyy-MM-dd HH")
    private Date createTime;

    /**
     * 邀约修改时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    private Date updateTime;

    @DateTimeFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+8")
    @Transient
    private Date endTime;

    /**
     * 希望邀约对象性别：0女 1男 2随便
     */
    @ApiModelProperty("希望邀约对象性别：0女 1男 2随便")
    @Column(name = "sex_want")
    @Excel(name="希望邀约对象性别：",replace = {"女_0","男_1","随便_2"})
    private Integer sexWant;

    @ApiModelProperty("个人邀请时自动创建聊天群")
    @Column(name = "chat_group_id")
    private String chatGroupId;

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
     * @return owner
     */
    public Long getOwner() {
        return owner;
    }

    /**
     * @param owner
     */
    public void setOwner(Long owner) {
        this.owner = owner;
    }


    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取主题
     *
     * @return content - 主题
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置主题
     *
     * @param content 主题
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取邀约类型
     *
     * @return type - 邀约类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置邀约类型
     *
     * @param type 邀约类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取邀约状态;0:有效；1：失效
     *
     * @return status - 邀约状态;0:有效；1：失效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置邀约状态;0:有效；1：失效
     *
     * @param status 邀约状态;0:有效；1：失效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取邀约成功状态：0：失败；1：成功
     *
     * @return result - 邀约成功状态：0：失败；1：成功
     */
    public Integer getResult() {
        return result;
    }

    /**
     * 设置邀约成功状态：0：失败；1：成功
     *
     * @param result 邀约成功状态：0：失败；1：成功
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * 获取邀约创建时间
     *
     * @return create_time - 邀约创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置邀约创建时间
     *
     * @param createTime 邀约创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取邀约修改时间
     *
     * @return update_time - 邀约修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置邀约修改时间
     *
     * @param updateTime 邀约修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取支付方式：0：AA，1：女生免费；2：赢家免费
     *
     * @return pay_way - 支付方式：0：AA，1：女生免费；2：赢家免费
     */
    public Integer getPayWay() {
        return payWay;
    }

    /**
     * 设置支付方式：0：AA，1：女生免费；2：赢家免费
     *
     * @param payWay 支付方式：0：AA，1：女生免费；2：赢家免费
     */
    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getInvitingNum() {
        return invitingNum;
    }

    public void setInvitingNum(Integer invitingNum) {
        this.invitingNum = invitingNum;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getSexWant() {
        return sexWant;
    }

    public void setSexWant(Integer sexWant) {
        this.sexWant = sexWant;
    }

    public String getChatGroupId() {
        return chatGroupId;
    }

    public void setChatGroupId(String chatGroupId) {
        this.chatGroupId = chatGroupId;
    }
}