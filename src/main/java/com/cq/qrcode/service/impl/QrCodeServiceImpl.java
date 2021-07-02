package com.cq.qrcode.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cq.qrcode.configuration.WebSocketServer;
import com.cq.qrcode.entity.QrLoginToken;
import com.cq.qrcode.mapper.QrLoginTokenMapper;
import com.cq.qrcode.service.QrCodeService;
import com.cq.qrcode.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 扫码登录功能实现类
 *
 * @author :chenqi
 * @date :2021/6/28 10:35
 */

@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Autowired
    private QrLoginTokenMapper qrLoginTokenMapper;

    @Override
    public JSONObject bindUserIdAndToken(String userId, String token,String projId){
        JSONObject jsonObject = new JSONObject();
        String msg="绑定成功！！！";
        int code=200;//默认成功
        try {
            QrLoginToken qrLoginToken = new QrLoginToken();
            qrLoginToken.setToken(token);
            qrLoginToken = qrLoginTokenMapper.selectOne(qrLoginToken);
            if (null == qrLoginToken) {
                msg="该token传递错误！";
                code=400;
                return jsonObject;
            }

            Date createDate = new Date(qrLoginToken.getCreateTime().getTime() + (1000 * 60 * 60 * 24 * Constant.Login.LOGIN_VALIDATION_TIME));
            Date nowDate = new Date();
            if (nowDate.getTime() > createDate.getTime()) {//当前时间大于校验时间
                msg="二维码失效！";
                code=400;
                return jsonObject;
            }

            qrLoginToken.setLoginTime(new Date());
            qrLoginToken.setUserId(userId);

            int i = qrLoginTokenMapper.updateById(qrLoginToken);
            if (i <= 0) {
                msg="服务器异常！";
                code=400;
            }
        }catch (Exception e){
            code= 400;
            msg="程序错误！！";
        }finally {
            try {
                jsonObject.put("code", code);
                jsonObject.put("msg", msg);
                jsonObject.put("userId", userId);
                if (projId != null) {
                    jsonObject.put("projId", projId);
                }
                WebSocketServer.sendInfo(jsonObject.toJSONString(), token);
            }catch (Exception e){
                System.out.println("此处为处理io异常");
            }
        }

        return jsonObject;
    }

    @Override
    public Map createQrImg() {
        Map map = new HashMap();
        map.put("uuid",UUID.randomUUID().toString().replaceAll("-", ""));
        map.put("token",UUID.randomUUID().toString().replaceAll("-", ""));
        qrLoginTokenMapper.insert(map);
        return map;
    }
}
