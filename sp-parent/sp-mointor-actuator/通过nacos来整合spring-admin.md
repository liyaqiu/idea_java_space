# 在使用Admin时，如果没有注册中心，需要各个客户端填写Admin服务端地址，而Admin是支持Nacos、Eureka、ZooKeeper等组件，可以直接从注册中心拉取服务实例
# https://doc.ruoyi.vip/ruoyi-cloud/cloud/monitor.html#%E9%9B%86%E6%88%90nacos

client 使用方式

1.去掉此配置
    spring:   # spring boot admin
        boot:
            admin:
                client:
                    url:
                        - http://192.168.88.1:9999
    
    2.引入nacos
    <dependency>
        <groupId>com.alibaba.csp</groupId>
        <artifactId>sentinel-datasource-nacos</artifactId>
    </dependency>
    
    3.填写nacos配置
    spring:
        application:
            name: sp-a-service  #nacos data-id = name+active+file-extension sp-a-service-dev.yaml
        profiles:
            active: dev
        cloud:
            nacos:
                server-addr: 192.168.109.1:8848


server 使用方式
    1.引入nacos
    <dependency>
        <groupId>com.alibaba.csp</groupId>
        <artifactId>sentinel-datasource-nacos</artifactId>
    </dependency>
    
    2.填写nacos配置
    spring:
        application:
            name: sp-a-service  #nacos data-id = name+active+file-extension sp-a-service-dev.yaml
        profiles:
            active: dev
        cloud:
            nacos:
                server-addr: 192.168.109.1:8848