package com.cq.qrcode.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author :chenqi
 * @date :2021/6/28 10:35
 */
public interface QrCodeService {

    JSONObject bindUserIdAndToken(String userId, String token, String projId);

    /**
     * 生成uuid和token
     * @return
     */
    Map createQrImg();
}
