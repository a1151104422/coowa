package com.example.demo.model;


import lombok.Data;

@Data
public class LogisticRequest {

    private String orderCode;
    private String expNo;
    private String CustomerName;
    private String CustomerPwd;

    private String shipperCode;//物流公司编码
    private Integer payType;//付款方式
    private String company;//发件人
    private String sendName;//发件人
    private String mobile;//发件人手机号
    private String sdProvinceName;//发件省
    private String sdCityName;//发件市
    private String sdExpAreaName;//发件区
    private String sdAddress;//发件详细地址
    private String name;//收件人
    private String monthCode;//月结编码
    private String tel;//电话
    private String provinceName;//收件省
    private String cityName;//收件市
    private String expAreaName;//收件市
    private String address;//收件人详细地址
    private String goodsName;//商品名称
    private Integer goodsQuantity;//商品数量
    private String remark;//备注

}
