package c.mj.note.ioc.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * create class OrderTest.java @version 1.0.0 by @author ChenMJ @date 2021-12-24 16:37:00
 */
public class OrderTest {
    @Test
    public void orderTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-smzq.xml");
        Orders orders = context.getBean("orders",Orders.class);
        System.out.println("next 4 可以使用了");
        System.out.println(orders);

        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("-------");
    }
}
