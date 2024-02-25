package com.a.b.czms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author eric
 * @date 2022/3/23 16:24
 **/
@Configuration
@EnableSwagger2
/*

原生swagger2 http://localhost:8080/swagger-ui.html
原生swagger2 http://localhost:8080/swagger-ui/index.html
改版的 knife4j http://localhost:8080/doc.html


Springboot微服务项目Swagger接口文档聚合
    https://doc.ruoyi.vip/ruoyi-cloud/cloud/swagger.html#%E6%8E%A5%E5%8F%A3%E6%A8%A1%E5%9D%97
    https://blog.csdn.net/weixin_52860572/article/details/125619176

*/

public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(true) //true则显示文档
                .securitySchemes(new ArrayList<ApiKey>(){
                    {
                        add(new ApiKey("Authorization","Authorization","header"));
                    }
                })
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.a.b.czms.controller"))//要扫描接口的目录
                //方式1，扫描所有有注解的api，用这种方式更灵活
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //方式2 扫描指定包中的swagger注解
                //.apis(RequestHandlerSelectors.basePackage("com.xxxx.zms"))
                //方式3 扫描所有
                //.apis(RequestHandlerSelectors.any())

                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("自定义标题信息")
                .description("自定义描述")
                .termsOfServiceUrl("自定义下载地址www.baidu.com")
                .version("1.0")
                .build();

    }

}
