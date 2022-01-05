package c.mj.note.ioc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * create class UserForSpringJUnit4Test.java @version 1.0.0 by @author ChenMJ @date 2021-12-29 17:01:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean1.xml")
public class UserForSpringJUnit4Test {

    @Autowired
    private UserService userService;

    @Test
    public void test1(){

        userService.add();
    }
}
