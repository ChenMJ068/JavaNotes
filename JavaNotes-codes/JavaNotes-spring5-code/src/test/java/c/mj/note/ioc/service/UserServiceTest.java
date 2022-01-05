package c.mj.note.ioc.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * create class UserServiceTest.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 16:33:00
 */
public class UserServiceTest {

    @Test
    public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService service = context.getBean("userService",UserService.class);
        System.out.println(service);
        service.add();
    }

}
