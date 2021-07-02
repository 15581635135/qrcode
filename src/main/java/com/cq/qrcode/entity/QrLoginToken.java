package com.cq.qrcode.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author :chenqi
 * @date :2021/6/28 10:53
 *
 */
@Data
public class QrLoginToken {

    /**
     * 用户id
     */
    private String userId;

    /**
     * token
     */
    private String token;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 登录时间
     */
    private Date loginTime;
}
