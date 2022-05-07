package design.designproxy.jdk.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DemoProxyFactory {

    public static void main(String[] args) {
        UserMapper userMapper = (UserMapper) new DemoProxyFactory().getProxy();
        System.out.println(userMapper.getClass());
        userMapper.insert();
        System.out.println("----------------------");
        System.out.println(userMapper.delete("100"));
    }

    public  Object getProxy(){
        return Proxy.newProxyInstance(UserMapper.class.getClassLoader(), new Class<?>[] {UserMapper.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(args!=null)
                for (int i = 0; i < args.length; i++) {
                    System.out.println(args[i]);
                }

                return 1;
            }
        });
    }


}
