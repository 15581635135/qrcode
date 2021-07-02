package com.cq.qrcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;


@SpringBootApplication
@MapperScan("com.cq.qrcode.mapper")
public class QrcodeApplication {

    public static void main(String[] args) {

        try {
            //加载配置文件
            Properties properties = new Properties();
            properties.load(QrcodeApplication.class.getResourceAsStream("/application.properties"));
            SpringApplication app = new SpringApplication(QrcodeApplication.class);
            app.setDefaultProperties(properties);
            app.run(args);
            System.out.println("QrcodeApplication run is success ....");
        }catch (Exception e){
            System.out.println("QrcodeApplication run is error ....");
        }
    }

}
