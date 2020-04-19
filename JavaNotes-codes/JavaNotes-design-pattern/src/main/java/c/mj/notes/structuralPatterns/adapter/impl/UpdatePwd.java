package c.mj.notes.structuralPatterns.adapter.impl;

import c.mj.notes.structuralPatterns.adapter.UserController;

/**
 * @author ChenMJ
 * @version UpdatePwd.class, v 0.1 2020/4/17 10:28  Exp$
 */
public class UpdatePwd implements UserController {
    @Override
    public void request() {
        System.out.println("我是修改密码请求");
    }
}
