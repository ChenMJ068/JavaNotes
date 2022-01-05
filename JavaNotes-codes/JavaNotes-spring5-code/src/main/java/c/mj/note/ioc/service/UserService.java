package c.mj.note.ioc.service;

import c.mj.note.ioc.dao.UserDao;

/**
 * create class UserService.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 16:23:00
 */
public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){
        userDao.add();
        System.out.println("--service add a data--");
    }
}
