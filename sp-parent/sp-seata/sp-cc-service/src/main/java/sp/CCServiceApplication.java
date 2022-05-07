package sp;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@MapperScan("sp.dao")
public class CCServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CCServiceApplication.class, args);
    }


}
