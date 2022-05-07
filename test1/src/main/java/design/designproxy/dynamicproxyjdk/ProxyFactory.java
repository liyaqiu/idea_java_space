package design.designproxy.dynamicproxyjdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyFactory {

    Object object ;

    public ProxyFactory(Object object) {
        this.object = object;
    }

    public  Object getProxy(){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //此处可以实现切入点，对哪些方法需要做拦截

                System.out.println("我被代理了");
                Object result = method.invoke(object, args);
                System.out.println("代理结束");
                return result;
            }
        });
    }
    public static Object getRPCProxy(Class<?> clazz){
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("rpc代理了");
                System.out.println("接口名:"+clazz.getName());
                System.out.println("方法名:"+method.getName());
                System.out.println("方法返回值类型:"+method.getReturnType());
                System.out.println("方法参数:"+Arrays.toString(method.getParameterTypes()));
                System.out.println("方法参数值:"+ Arrays.toString(args));
                System.out.println("rpc代理结束");
                return "success";
            }
        });
    }

}
