package com.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author eric
 * @date 2022/5/11 11:17
 **/
public class MyFactory implements FactoryBean<MyDog> {
    @Override
    public MyDog getObject() throws Exception {
        return new MyDog();
    }

    @Override
    public Class<?> getObjectType() {
        return MyDog.class;
    }
}
