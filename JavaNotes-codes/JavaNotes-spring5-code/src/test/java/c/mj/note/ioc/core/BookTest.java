package c.mj.note.ioc.core;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * create class BookTest.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 15:57:00
 */
public class BookTest {

    @Test
    public void testBook(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        Book book = context.getBean("book",Book.class);
        System.out.println(book);
        book.toString();

    }
}
