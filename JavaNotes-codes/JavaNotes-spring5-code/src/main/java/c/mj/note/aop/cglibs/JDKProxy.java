package c.mj.note.aop.cglibs;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * create class JDKProxy.java @version 1.0.0 by @author ChenMJ @date 2021-12-28 16:19:00
 */
public class JDKProxy {

    public static void main(String[] args) {
        Class[] interfaces = {UserDao.class};

        UserDaoImpl userDao = new UserDaoImpl();
        UserDao dao = (UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(),interfaces,new UserDaoProxy(userDao));
        int result =  dao.add(1,2);
        //dao.update("str1234");
        System.out.println("result:"+result);
    }
}

class UserDaoProxy implements InvocationHandler{

    private Object obj;

    public UserDaoProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //增强前执行
        System.out.println("方法之前执行。。。。"+method.getName()+":传递的参数。。"+ Arrays.toString(args));

        //被增强的方法执行
        Object object = method.invoke(obj,args);

        //之后执行
        System.out.println("方法执行之后执行。。"+obj);

        return object;
    }
}