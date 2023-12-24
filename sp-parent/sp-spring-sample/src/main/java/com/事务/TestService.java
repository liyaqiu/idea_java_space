package com.事务;

import org.apache.tomcat.jni.Error;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * @author eric
 * @date 2022/12/12 23:55
 **/
@Component
public class TestService {
    /**
     * Spring事务默认只对RuntimeException和Error异常进行回滚，如果特殊情况则需要使用rollbackFor指定才能进行回滚（可以指定父类异常）
     * */
    //（可以指定父类异常）
    @Transactional(rollbackFor = {Exception.class,SQLException.class})
    public void test1() throws SQLException {
        throw new SQLException("");
    }


    @Transactional
    public void test2() throws SQLException {
        throw new RuntimeException("");
    }
}
