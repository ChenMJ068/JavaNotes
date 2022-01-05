package c.mj.note.ioc.core;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * create class UserTest.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 15:36:00
 */

public class UserTest {

    @Test
    public void testGet(){
        BeanFactory factory = new ClassPathXmlApplicationContext("bean.xml");
        UserDemo userDemo = factory.getBean("user",UserDemo.class);
        System.out.println(userDemo);
        userDemo.get();
    }
}
