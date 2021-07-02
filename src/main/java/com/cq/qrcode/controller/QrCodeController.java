package com.cq.qrcode.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cq.qrcode.common.ResultConstant;
import com.cq.qrcode.common.mResult;
import com.cq.qrcode.service.QrCodeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 扫码登录功能实现
 *
 * @author :chenqi
 * @date :2021/6/28 10:34
 */

@RestController
@RequestMapping("/qrCode")
//@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class QrCodeController {

    @Resource
    private QrCodeService qrCodeService;

    //获取登录二维码、放入Token
    @GetMapping(value = "/getLoginQr")
    public mResult createCodeImg(HttpServletRequest request, HttpServletResponse response){
        mResult mresult=new mResult();
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            // 指定特定域名可以访问response.setHeader("Access-Control-Allow-Origin", "http:localhost:8080/");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            //"Access-Control-Expose-Headers"：首先得注意是"Access-Control-Expose-Headers"进行跨域请求时响应头部中的一个字段，对于同域请求，
            // 响应头部是没有这个字段的。这个字段中列举的 header 字段就是服务器允许暴露给客户端访问的字段。
            response.setHeader("Access-Control-Expose-Headers","uuid,token");//不把这个uuid暴露的话，前台无法访问这类不安全的值
            //生成一个UUID插入 数据库的表里
            Map map= qrCodeService.createQrImg();
            response.setHeader("uuid",map.get("uuid").toString());
            response.setHeader("token",map.get("token").toString());
            // 这里是开源工具类 hutool里的QrCodeUtil 网址：http://hutool.mydoc.io/
            // 生成指定url对应的二维码到文件，宽和高都是300像素
            QrCodeUtil.generate(map.get("uuid").toString(), 300, 300, "jpg",response.getOutputStream());
            mresult.setMcode(ResultConstant.CODE.SUCCESS);
            mresult.setMmsg(ResultConstant.MESSAGE.DEFAULT_SUCCESS_MESSAGE);
            mresult.setMrows(map);
        } catch (Exception e) {
            mresult.setMcode(ResultConstant.CODE.ERROR);
            mresult.setMmsg(ResultConstant.MESSAGE.DEFAULT_ERROR_MESSAGE);
            e.printStackTrace();
        }
        return mresult;
    }

    /**
     * 确认身份接口：确定身份以及判断是否二维码过期等
     * @return
     */
    @GetMapping(value = "/bindUserIdAndToken",produces = {"application/json;charset=UTF-8"})
    public String bindUserIdAndToken(HttpServletRequest request,HttpServletResponse response){

        try {
            response.setContentType("text/plain");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Access-Control-Allow-Origin", "*");
            String token=request.getParameter("token");
            String userId=request.getParameter("userId");
            String projId=request.getParameter("projId");
            String callback=request.getParameter("callback1");

            JSONObject jsonObject = qrCodeService.bindUserIdAndToken(userId, token, projId);
            //需要返回jsonp格式的数据
            return callback+"("+ JSON.toJSONString(jsonObject)+")";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
