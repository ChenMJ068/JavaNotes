package c.mj.note.ioc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * create class UserForSpringJUnit5Test1.java @version 1.0.0 by @author ChenMJ @date 2021-12-29 17:14:00
 */
//使用复合注解方式
@SpringJUnitConfig(locations = "classpath:bean1.xml")
public class UserForSpringJUnit5Test1 {
    @Autowired
    private UserService userService;

    @Test
    public void test1(){

        userService.add();
    }
}
