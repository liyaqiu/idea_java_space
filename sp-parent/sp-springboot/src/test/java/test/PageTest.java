package test;

import com.Launcher;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDto;
import com.controller.UserController;
import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lyq
 * @date 2021/12/4 15:31
 */
@SpringBootTest(classes = Launcher.class)
@Slf4j
public class PageTest {

    @Autowired
    UserController userController;
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @Test
    public void test0() {
        log.info("这个对象：{}", userController);
    }

    @Test
    public void test1() {
        User user = userDao.selectById(1);
        log.info("{}", user);
    }

    @Test
    public void test2() {
        IPage<User> page = new PageDto<>(2, 2);
        userDao.selectPage(page, null);
        for (User user : page.getRecords()) {
            log.info("当前页{}", user);
        }
        log.info("当前页{}", page.getCurrent());

    }

    @Test
    public void test3() {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper();
        int id = 1;
        query.eq(id!=0,User::getName, 1);
        userDao.selectList(query);
    }

    @Test
    public void test4() {
        User user = userService.getById(1);
        log.info("{}",user);
    }
}
