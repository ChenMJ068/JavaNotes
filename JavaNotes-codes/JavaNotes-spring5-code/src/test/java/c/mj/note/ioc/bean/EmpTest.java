package c.mj.note.ioc.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * create class EmpTest.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 16:44:00
 */
public class EmpTest {

    @Test
    public void empTest(){
        //ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        Emp emp = context.getBean("emp",Emp.class);
        System.out.println(emp);
        emp.toString();
    }
}
