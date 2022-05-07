package com;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author lyq
 * @date 2022/1/18 5:52
 */
@SpringBootApplication
public class ESAppliction {
    public static void main(String[] args) {
        SpringApplication.run(ESAppliction.class, args);
    }


    @Bean
    public RestHighLevelClient esClient(){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.0.203:9200")));
        return client;
    }
}



