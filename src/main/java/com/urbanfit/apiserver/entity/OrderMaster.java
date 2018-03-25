package com.urbanfit.apiserver.entity;

import com.urbanfit.apiserver.common.base.BaseModel;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/25.
 */
public class OrderMaster extends BaseModel{
    /**
     * 是否使用优惠码  0：否  1：是
     */
    public static final int USE_COUPON = 0;
    public static final int NO_USE_COUPON = 1;
    /**
     * 支付状态 0：未支付  1：已支付  2：已退款
     */
    public static final int STATUS_WAITING_PAY = 0;
    public static final int STATUS_PAYES = 1;
    public static final int STATUS_REFUND = 2;
    /**
     * 支付类型  0：支付宝  1：微信
     */
    public static final int PAYMENT_ALIPAY = 0;
    public static final int PAYMENT_WECHAT = 1;

    private Integer orderId;
    private String orderNum;
    private Integer clientId;
    private Integer courseId;
    private String courseDistrict;
    private Double price;
    private Double payPrice;
    /**
     * 是否使用优惠码  0：否  1：是
     */
    private int isUseCoupon;
    private Integer couponId;
    private String couponNum;
    private Double couponPercent;
    /**
     * 支付类型  0：支付宝  1：微信
     */
    private Integer payment;
    /**
     * 支付状态 0：未支付  1：已支付  2：已退款
     */
    private int status;
    private Date createTime;
    private Date payTime;

    private String clientName;
    private String clientMobile;
    private Integer courseType;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public int getIsUseCoupon() {
        return isUseCoupon;
    }

    public void setIsUseCoupon(int isUseCoupon) {
        this.isUseCoupon = isUseCoupon;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(String couponNum) {
        this.couponNum = couponNum;
    }

    public Double getCouponPercent() {
        return couponPercent;
    }

    public void setCouponPercent(Double couponPercent) {
        this.couponPercent = couponPercent;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getCourseDistrict() {
        return courseDistrict;
    }

    public void setCourseDistrict(String courseDistrict) {
        this.courseDistrict = courseDistrict;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public String getClientMobile() {
        return clientMobile;
    }

    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
