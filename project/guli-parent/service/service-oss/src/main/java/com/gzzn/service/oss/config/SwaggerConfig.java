package com.gzzn.service.oss.config;

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
 * @date 2022/3/23 16:24
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //http://localhost:8002/swagger-ui.html
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(true) //true则显示文档
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gzzn.service.oss.test.controller"))//要扫描接口的目录
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("自定义标题信息")
                .description("自定义描述")
                //.termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();

    }

}
