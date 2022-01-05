package c.mj.note.ioc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * create class UserForSpringJUnit5Test.java @version 1.0.0 by @author ChenMJ @date 2021-12-29 17:07:00
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:bean1.xml")
public class UserForSpringJUnit5Test {
    
    @Autowired
    private UserService userService;

    @Test
    public void test1(){

        userService.add();
    }
}
