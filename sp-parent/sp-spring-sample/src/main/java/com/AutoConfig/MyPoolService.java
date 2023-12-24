package com.AutoConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * @author eric
 * @date 2022/5/11 16:32
 **/
/**
 * 方式1 利用 EnableMyPoolService 来启用
 * @Target({ElementType.TYPE})
 * @Retention(RetentionPolicy.RUNTIME)
 * @Documented
 * @Import(MyPoolService.class)
 * public @interface EnableMyPoolService {
 * }
 * */
/**
 * 方式2 在META-INF文件中添加spring.factories文件
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
 *     com.AutoConfig.MyPoolService
 * */
@EnableConfigurationProperties(MyPoolConfig.class)
//判断是否是web项目
@ConditionalOnWebApplication
@Slf4j
public class MyPoolService {
    private CatConfig catConfig;
    private DogConfig dogConfig;

    MyPoolConfig myPoolConfig;

    public MyPoolService(MyPoolConfig myPoolConfig) {
        this.myPoolConfig = myPoolConfig;
        catConfig = new CatConfig();
        dogConfig = new DogConfig();

        if (myPoolConfig.getCatConfig()==null || StringUtils.isEmpty(myPoolConfig.getCatConfig().getName())) {
            catConfig.setName("默认的cat name");
        }else{
            catConfig.setName(myPoolConfig.getCatConfig().getName());
        }
        if (myPoolConfig.getCatConfig()==null || StringUtils.isEmpty(myPoolConfig.getCatConfig().getAge())) {
            catConfig.setAge("默认的cat age");
        }else{
            catConfig.setAge(myPoolConfig.getCatConfig().getAge());
        }

        if (myPoolConfig.getDogConfig()==null || StringUtils.isEmpty(myPoolConfig.getDogConfig().getName())) {
            dogConfig.setName("默认的dog name");
        }else{
            dogConfig.setName(myPoolConfig.getDogConfig().getName());
        }
        if (myPoolConfig.getDogConfig()==null || StringUtils.isEmpty(myPoolConfig.getDogConfig().getAge())) {
            dogConfig.setAge("默认的dog age");
        }else{
            dogConfig.setAge(myPoolConfig.getDogConfig().getAge());
        }
    }

    public void displayConfig(){
        log.info("CatConfig:{}",catConfig);
        log.info("DogConfig:{}",dogConfig);
    }


}
