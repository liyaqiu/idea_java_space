package com.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;
import com.mybatisplus.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/mybatisplus")
//@Slf4j
public class MybatisPlusController {

    private static final Logger log = LoggerFactory.getLogger("com.mybatisplus");

    @Autowired
    UserMapper userMapper;


    @GetMapping("/test")
    public void test() {
        log.debug("peopleMapper class :{}", userMapper.getClass());
        User user = userMapper.selectById("1");
        log.info("user {}",user);
    }
    @GetMapping("/test2")
    public void test2() {
        User user = userMapper.queryUserById("1");
        log.info("user {}",user);
    }

    @GetMapping("/test3")
    public void test3() {
        User user = userMapper.selectUserById("1");
        log.info("user {}",user);
    }

    /**
     * 添加分页插件拦截器
     * */
    @Bean
    public MybatisPlusInterceptor mPPageInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        log.info("mybatis plus 分页拦截器加载。。。");
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDialect(new MySqlDialect());
        //paginationInnerInterceptor.setMaxLimit();
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

    @GetMapping("/test4")
    public void test4() {
        log.debug("分页查询");
        IPage<User> page = new PageDto<>(2, 2);
        userMapper.selectPage(page, null);
        for (User user : page.getRecords()) {
            log.info("当前页{}", user);
        }
        log.info("当前页{}", page.getCurrent());
    }

    //关联查询多字段映射
    @GetMapping("/test5")
    public void test5() {
        log.debug("一对多");
        List<User> userList = userMapper.oneToMany();
        for (User user : userList) {
            log.info("当前页{}", user);
        }
    }

    //关联查询多字段映射
    @GetMapping("/test6")
    public void test6() {
        log.debug("一对多--注解方法");
        List<User> userList = userMapper.oneToManyAnnotation();
        for (User user : userList) {
            log.info("当前页{}", user);
        }
    }

    //关联查询多字段映射
    @GetMapping("/test7")
    public void test7() {
        log.debug("多对多");
        List<User> userList = userMapper.manyToMany();
        for (User user : userList) {
            log.info("当前页{}", user);
        }
    }

    //关联查询多字段映射
    @GetMapping("/test8")
    public void test8() {
        log.debug("多对多--注解");
        List<User> userList = userMapper.manyToManyAnnotation();
        for (User user : userList) {
            log.info("当前页{}", user);
        }
    }


}
