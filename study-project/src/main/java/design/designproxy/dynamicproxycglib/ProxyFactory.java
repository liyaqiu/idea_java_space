package design.designproxy.dynamicproxycglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory<T> implements MethodInterceptor {

    private T object;

    public ProxyFactory(T object) {
        this.object = object;
    }

    public T getProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(this);
        return (T)enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理开始");
        Object result = method.invoke(object, objects);
        System.out.println("cglib代理结束");
        return result;
    }
}
