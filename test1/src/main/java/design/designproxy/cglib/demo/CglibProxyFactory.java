package design.designproxy.cglib.demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory implements MethodInterceptor {

    public static void main(String[] args) {
        UserMapper userMapper = new CglibProxyFactory().getProxy();
        System.out.println(userMapper.getClass());
        userMapper.insert();
        System.out.println("----------------------");
        System.out.println(userMapper.delete("100"));
    }


    public UserMapper getProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserMapper.class);
        enhancer.setCallback(this);
        return (UserMapper)enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("cglib 代理。。。");
        if(args!=null)
            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
            }
        return 1;
    }
}
