package c.mj.notes.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * create class SmsInvocationHandler.java @version 1.0.0 by @author  time 2021年05月20日 16:16:00
 */
public class SmsInvocationHandler implements InvocationHandler {

    private final Object target;

    public SmsInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method :"+method.getName());
        Object result = method.invoke(target,args);
        System.out.println("after method :"+method.getName());

        return result;
    }
}
