package c.mj.notes.structuralPatterns.adapter.impl;

import c.mj.notes.structuralPatterns.adapter.UserController;

/**
 * @author ChenMJ
 * @version Login.class, v 0.1 2020/4/17 10:24  Exp$
 */
public class Login implements UserController {
    @Override
    public void request() {
        System.out.println("我是登录请求");
    }
}
