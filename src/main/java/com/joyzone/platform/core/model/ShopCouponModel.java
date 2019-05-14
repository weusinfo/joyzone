package com.joyzone.platform.core.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import javax.persistence.*;

@Table(name = ShopCouponModel.TABLE_NAME)
@ApiModel("体验券信息")
public class ShopCouponModel extends BaseModel{

    protected static final String TABLE_NAME = "shop_coupon";

    @Excel(name="体验券标识")
    @Id
    @ApiModelProperty("主键")
    private Long id;

    @Column(name = "shop_id")
    @ApiModelProperty("店家ID")
    private Long shopId;

    /**
     * 活动名称
     */
    @Excel(name="体验券名称")
    @ApiModelProperty("体验券名称")
    private String name;


    /**
     * 体验券店家名称
     */
    @Column(name = "shop_name")
    @Excel(name="店家名称")
    @ApiModelProperty("店家名称")
    private String shopName;

    /**
     * 体验券店家种类ID
     */
    @Column(name = "shop_type_id")
    @ApiModelProperty("体验券店家种类ID")
    private Long shopTypeId;

    /**
     * 体验券店家种类名称
     */
    @Excel(name="体验券店家种类名称")
    @Column(name = "shop_type_name")
    @ApiModelProperty("体验券店家种类名称")
    private String shopTypeName;

    /**
     * 价格
     */
    @Excel(name="体验券价格")
    @ApiModelProperty("体验券价格")
    private Long price;

    /**
     * 参加人数限制
     */
    @Excel(name="限制人数")
    @ApiModelProperty("限制人数")
    private Integer number;

    /**
     * 活动背景图URL
     */
    @Column(name = "back_img")
    @ApiModelProperty("活动背景图URL")
    private String backImg;

    /**
     * 状态：0 失效 1 正常
     */
    @Excel(name="状态",replace= {"失效_0","正常_1"})
    @ApiModelProperty("状态：0 失效 1 正常")
    private Integer status;

    /**
     * 状态：0 失效 1 正常
     */
    @Excel(name="结果",replace= {"组队中_0","组队成功_1","组队失败_2"})
    @ApiModelProperty("结果：0：组队中；1：组队成功；2：组队失败")
    private Integer result;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "start_time")
    @Excel(name="体验时间",exportFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("开始时间 yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @Column(name = "end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("结束时间 yyyy-MM-dd HH:mm:ss")
    @Excel(name="结束时间",exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name="创建时间",exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name="更新时间",exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 活动内容
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
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取活动名称
     *
     * @return name - 活动名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置活动名称
     *
     * @param name 活动名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 获取活动背景图URL
     *
     * @return back_img - 活动背景图URL
     */
    public String getBackImg() {
        return backImg;
    }

    /**
     * 设置活动背景图URL
     *
     * @param backImg 活动背景图URL
     */
    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    /**
     * 获取状态：0 失效 1 正常
     *
     * @return status - 状态：0 失效 1 正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0 失效 1 正常
     *
     * @param status 状态：0 失效 1 正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
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

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    /**
     * 获取活动内容
     *
     * @return content - 活动内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置活动内容
     *
     * @param content 活动内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(Long shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    public String getShopTypeName() {
        return shopTypeName;
    }

    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }
}