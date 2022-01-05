package c.mj.note.ioc.core;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * create class OrdersTest.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 16:06:00
 */
public class OrdersTest {

    @Test
    public void ordersTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Orders orders = context.getBean("orders",Orders.class);
        System.out.println(orders);
        orders.toString();
    }
}
