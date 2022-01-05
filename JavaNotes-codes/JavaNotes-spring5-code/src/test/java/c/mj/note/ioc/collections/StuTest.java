package c.mj.note.ioc.collections;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * create class StuTest.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 17:11:00
 */
public class StuTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-collection.xml");
        Stu stu = context.getBean("stu",Stu.class);
        System.out.println(stu.toString());
    }
}
