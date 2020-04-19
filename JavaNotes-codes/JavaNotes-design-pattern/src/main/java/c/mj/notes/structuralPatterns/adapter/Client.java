package c.mj.notes.structuralPatterns.adapter;

import c.mj.notes.structuralPatterns.adapter.impl.Login;
import c.mj.notes.structuralPatterns.adapter.impl.UpdatePwd;
import c.mj.notes.structuralPatterns.adapter.impl.UserCenter;

/**
 * @author ChenMJ
 * @version Client.class, v 0.1 2020/4/17 10:39  Exp$
 */
public class Client {
    public static void main(String[] args) {
        AdapterHandler handler = new AdapterHandler();

        UserController login = (Login) handler.handler("login");
        login.request();

        UserController pwd = (UpdatePwd) handler.handler("updatePwd");
        pwd.request();

        UserController center = (UserCenter) handler.handler("userCenter");
        center.request();
    }
}
