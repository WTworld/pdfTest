package com.u_anywhere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan(basePackages = {"com.u_anywhere.mappers"})
public class IAPDFTestApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(IAPDFTestApp.class)
//        .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
        .run(args);
    }

}