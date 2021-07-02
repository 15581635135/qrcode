package com.cq.qrcode.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author :chenqi
 * @date :2021/6/28 10:47
 */
@Component
public class QrCodeConfiguration {

    /**
     * WebSocket的支持
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
