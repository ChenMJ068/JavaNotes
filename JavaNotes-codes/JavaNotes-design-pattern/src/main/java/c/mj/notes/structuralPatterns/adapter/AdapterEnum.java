package c.mj.notes.structuralPatterns.adapter;

import c.mj.notes.structuralPatterns.adapter.impl.Login;
import c.mj.notes.structuralPatterns.adapter.impl.UpdatePwd;
import c.mj.notes.structuralPatterns.adapter.impl.UserCenter;

/**
 * @author ChenMJ
 * @version AdapterEnum.class, v 0.1 2020/4/17 10:30  Exp$
 */
public enum AdapterEnum {
    /***/
    LOGIN {
        @Override
        public Login getObject() {
            return new Login();
        }
    },
    UPDATE_PWD {
        @Override
        public UpdatePwd getObject() {
            return new UpdatePwd();
        }
    },
    USER_CENTER {
        @Override
        public UserCenter getObject() {
            return new UserCenter();
        }
    };

    public Object getObject() {
        return null;
    }
}
