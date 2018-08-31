package com.roowoo.hadoop;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfoDTO {
    private Long id;

    private String orderNo;

    private Long merchantId;

    private String merchantName;

    private String hopeTime;

    private String deliverAddress;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String deliverType;

    private Long horsemanId;

    private String horsemanName;

    private String horsemanPhone;

    private BigDecimal orderAmount;

    private Long mycouponId;

    private BigDecimal discountAmount;

    private Long mycashId;

    private BigDecimal cashAmount;

    private BigDecimal packAmount;

    private BigDecimal deliverAmount;

    private BigDecimal realAmount;

    private String nickName;

    private Long userId;

    private Byte payType;

    private Byte orderStatus;

    private String extra1;

    private String extra2;

    private String extra3;

    private String extra4;

    private String extra5;

    private String extra6;

    private String extra7;

    private String extra8;

    private String extra9;

    private String extra10;

    private String extra11;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getHopeTime() {
        return hopeTime;
    }

    public void setHopeTime(String hopeTime) {
        this.hopeTime = hopeTime == null ? null : hopeTime.trim();
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress == null ? null : deliverAddress.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(String deliverType) {
        this.deliverType = deliverType == null ? null : deliverType.trim();
    }

    public Long getHorsemanId() {
        return horsemanId;
    }

    public void setHorsemanId(Long horsemanId) {
        this.horsemanId = horsemanId;
    }

    public String getHorsemanName() {
        return horsemanName;
    }

    public void setHorsemanName(String horsemanName) {
        this.horsemanName = horsemanName == null ? null : horsemanName.trim();
    }

    public String getHorsemanPhone() {
        return horsemanPhone;
    }

    public void setHorsemanPhone(String horsemanPhone) {
        this.horsemanPhone = horsemanPhone == null ? null : horsemanPhone.trim();
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getMycouponId() {
        return mycouponId;
    }

    public void setMycouponId(Long mycouponId) {
        this.mycouponId = mycouponId;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Long getMycashId() {
        return mycashId;
    }

    public void setMycashId(Long mycashId) {
        this.mycashId = mycashId;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getPackAmount() {
        return packAmount;
    }

    public void setPackAmount(BigDecimal packAmount) {
        this.packAmount = packAmount;
    }

    public BigDecimal getDeliverAmount() {
        return deliverAmount;
    }

    public void setDeliverAmount(BigDecimal deliverAmount) {
        this.deliverAmount = deliverAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1 == null ? null : extra1.trim();
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2 == null ? null : extra2.trim();
    }

    public String getExtra3() {
        return extra3;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3 == null ? null : extra3.trim();
    }

    public String getExtra4() {
        return extra4;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4 == null ? null : extra4.trim();
    }

    public String getExtra5() {
        return extra5;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5 == null ? null : extra5.trim();
    }

    public String getExtra6() {
        return extra6;
    }

    public void setExtra6(String extra6) {
        this.extra6 = extra6 == null ? null : extra6.trim();
    }

    public String getExtra7() {
        return extra7;
    }

    public void setExtra7(String extra7) {
        this.extra7 = extra7 == null ? null : extra7.trim();
    }

    public String getExtra8() {
        return extra8;
    }

    public void setExtra8(String extra8) {
        this.extra8 = extra8 == null ? null : extra8.trim();
    }

    public String getExtra9() {
        return extra9;
    }

    public void setExtra9(String extra9) {
        this.extra9 = extra9 == null ? null : extra9.trim();
    }

    public String getExtra10() {
        return extra10;
    }

    public void setExtra10(String extra10) {
        this.extra10 = extra10 == null ? null : extra10.trim();
    }

    public String getExtra11() {
        return extra11;
    }

    public void setExtra11(String extra11) {
        this.extra11 = extra11 == null ? null : extra11.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}