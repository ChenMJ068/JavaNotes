package c.mj.notes.base.proxy;

import java.lang.reflect.Proxy;

/**
 * create class ProxyFactory.java @version 1.0.0 by @author chenMJ  time 2021年05月20日 16:20:00
 */
public class ProxyFactory {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new SmsInvocationHandler(target)
        );
    }
}
