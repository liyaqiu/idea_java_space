package test;

import com.Launcher;
import com.entity.User;
import com.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

/**
 * @author lyq
 * @date 2022/3/8 17:19
 * //加Transactional测试防止数据插入数据
 */
@SpringBootTest(classes = Launcher.class)
@Transactional
//@Rollback(value = true)
public class ServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void test0(){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("luyaqiu");
        user.setAge(15);
        userService.save(user);
    }
}
