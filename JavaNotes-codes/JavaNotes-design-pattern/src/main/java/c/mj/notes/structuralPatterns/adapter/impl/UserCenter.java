package c.mj.notes.structuralPatterns.adapter.impl;

import c.mj.notes.structuralPatterns.adapter.UserController;

/**
 * @author ChenMJ
 * @version UserCenter.class, v 0.1 2020/4/17 10:29  Exp$
 */
public class UserCenter implements UserController {
    @Override
    public void request() {
        System.out.println("我是个人中心");
    }
}
