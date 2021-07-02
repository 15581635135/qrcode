package com.cq.qrcode.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mResult {
    /*  100	成功
    201	无授权码
    202	请求格式错误
    203	拒绝访问
    301	参数错误
    302	重复数据
    500	服务器异常
    501	接口接口服务异常

    999	其他未知错误*/
    @JSONField(ordinal = 1)
    private int mcode=999;
    @JSONField(ordinal = 2)
    private String mmsg;
    @JSONField(ordinal = 3)
    private Date mdate=new Date();
    @JSONField(ordinal = 4)
    private Object mrows;
    private int mtotal=0;

    public mResult() {
        super();
    }
    public mResult(int stateCode, String message, Object obj, int mtotal) {
        super();
        this.mcode = stateCode;
        this.mmsg = message;
        this.mrows=obj;
        this.mtotal=mtotal;
        Date date = new Date();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.mdate=sdf.parse(sdf.format(date));
        } catch(ParseException ex){

        }
    }
    public int getMcode(){return this.mcode;}
    public void setMcode(int mcode){
        this.mcode = mcode;
        switch(this.mcode){
            case 100:
                this.mmsg="成功";
                break;
            case 201:
                this.mmsg="无授权码";
                break;
            case 202:
                this.mmsg="请求格式错误";
                break;
            case 203:
                this.mmsg="拒绝访问";
                break;
            case 301:
                this.mmsg="参数错误";
                break;
            case 302:
                this.mmsg="重复数据";
                break;
            case 500:
                this.mmsg="服务器异常";
                break;
            case 501:
                this.mmsg="接口接口服务异常";
                break;
            case 3401:
                this.mmsg="登录成功";
                break;
            case 3403:
                this.mmsg="密码不正确";
                break;
            case 3404:
                this.mmsg="用户不存在";
                break;
            case 5404:
                this.mmsg="退出失败";
                break;
            default:
                this.mmsg="其他未知错误";
                break;
        }
    }
    public String getMmsg(){
        if(this.mmsg==null){
            switch(this.mcode){
                case 100:
                    this.mmsg="成功";
                    break;
                case 201:
                    this.mmsg="无授权码";
                    break;
                case 202:
                    this.mmsg="请求格式错误";
                    break;
                case 203:
                    this.mmsg="拒绝访问";
                    break;
                case 301:
                    this.mmsg="参数错误";
                    break;
                case 302:
                    this.mmsg="重复数据";
                    break;
                case 500:
                    this.mmsg="服务器异常";
                    break;
                case 501:
                    this.mmsg="接口接口服务异常";
                    break;
                case 3401:
                    this.mmsg="登录成功";
                    break;
                case 3403:
                    this.mmsg="密码不正确";
                    break;
                case 3404:
                    this.mmsg="用户不存在";
                    break;
                default:
                    this.mmsg="其他未知错误";
                    break;
            }
        }return this.mmsg;}
    public void setMmsg(String mmsg){this.mmsg = mmsg == null ? null : mmsg.trim();}
    public Object getMrows(){return this.mrows;}
    public void setMrows(Object mrows){this.mrows = mrows;}
    public int getMtotal(){return this.mtotal;}
    public void setMtotal(int mtotal){this.mtotal = mtotal;}
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getMdate(){ return this.mdate;}
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setMdate(Date mdate){this.mdate = mdate;}
}
