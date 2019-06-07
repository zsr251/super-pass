package com.javasoso.pass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = {"com.javasoso.pass.mapper"})
@EnableAspectJAutoProxy
public class SuperPassApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperPassApplication.class, args);
    }
}
