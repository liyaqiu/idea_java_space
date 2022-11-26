package com.sfkj.service.hospital.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author eric
 * @date 2022/11/22 19:02
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //http://localhost:9999/swagger-ui.html
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(true) //true则显示文档
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sfkj.service.hospital.api.controller"))//要扫描接口的目录
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("医院系统")
                .description("医院系统api")
                //.termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();

    }

}
