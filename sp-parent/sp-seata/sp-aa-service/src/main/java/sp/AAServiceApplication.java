package sp;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "feign.client")
@MapperScan({"sp.dao","sp.tcc.dao"})
public class AAServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AAServiceApplication.class, args);
    }


}
