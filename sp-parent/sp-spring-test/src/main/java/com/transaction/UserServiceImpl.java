package com.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author lyq
 * @date 2022/3/3 2:43
 */
@Service
@Slf4j
public class UserServiceImpl {

    @Autowired
    UserMapper1 userDao;

    //根据调用者是否有事务来执行事务，当事务传播用SUPPORTS，那么隔离级别会根据调用者来觉得，此时当前这个Isolation.REPEATABLE_READ属性不生效
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.SUPPORTS)
    public void test0(){
        log.info("{}","service test0");
        userDao.insert(new User(UUID.randomUUID().toString(), "liyaqiu", "12"));
        int a = 1/0;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,timeout = 5)
    public void test1() {
        log.info("{} {}","service test1",this.getClass());
        //userDao.insert(new User(UUID.randomUUID().toString(), "liyaqiu", "12"));
        User user  = new User("1", "liyaqiu11", "21");
        userDao.updateById(user);
    }
}
