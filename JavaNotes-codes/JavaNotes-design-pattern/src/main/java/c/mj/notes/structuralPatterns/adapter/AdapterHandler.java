package c.mj.notes.structuralPatterns.adapter;

/**
 * @author ChenMJ
 * @version Handler.class, v 0.1 2020/4/17 10:09  Exp$
 */
public class AdapterHandler<T> {
    public T handler(String url){
        T user = null;

        if (url.equalsIgnoreCase("login")){
            user = (T) AdapterEnum.LOGIN.getObject();
        }
        if (url.equalsIgnoreCase("updatePwd")){
            user = (T) AdapterEnum.UPDATE_PWD.getObject();
        }
        if (url.equalsIgnoreCase("userCenter")){
            user = (T) AdapterEnum.USER_CENTER.getObject();
        }
        return user;
    }
}
