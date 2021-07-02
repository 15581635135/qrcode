package com.cq.qrcode.mapper;

import com.cq.qrcode.entity.QrLoginToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author :chenqi
 * @date :2021/6/28 10:54
 */
@Repository
@Mapper
public interface QrLoginTokenMapper {

    QrLoginToken selectOne (QrLoginToken qrLoginToken);

    int updateById (QrLoginToken qrLoginToken);

    void insert(Map map);
}
