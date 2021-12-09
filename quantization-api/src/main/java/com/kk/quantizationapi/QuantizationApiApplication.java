package com.kk.quantizationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@EnableWebMvc
@MapperScan("com.kk.**.dao.mapper")
public class QuantizationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuantizationApiApplication.class, args);
    }

}
