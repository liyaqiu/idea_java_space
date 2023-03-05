package com.gzzn.service.edu.config;

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

    //http://localhost:8001/swagger-ui.html
    //http://localhost:8080/swagger-ui/index.html
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(true) //true则显示文档
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gzzn.service.edu.test.controller"))//要扫描接口的目录
                //方式1，扫描所有有注解的api，用这种方式更灵活
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //方式2 扫描指定包中的swagger注解
                //.apis(RequestHandlerSelectors.basePackage("com.ruoyi.zms"))
                //方式3 扫描所有
                //.apis(RequestHandlerSelectors.any())
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
