package c.mj.note.aop.cglibs;

/**
 * create class UserDaoImpl.java @version 1.0.0 by @author ChenMJ @date 2021-12-28 16:18:00
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int add(int a, int b) {
        System.out.println("----add run-----");
        return a+b;
    }

    @Override
    public String update(String id) {
        return id;
    }
}
