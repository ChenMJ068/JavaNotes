package c.mj.notes.base.proxy;

/**
 *
 * @author admin
 * @version 1.0.0
 * @ClassName Main.java
 * @Description TODO
 * @createTime 2021年05月20日 15:54:00
 */
public class Main {
    public static void main(String[] args) {
        //静态代理方式
        //SmsInterface smsInterface = new SmsInterfaceImp();
        //SmsProxy proxy = new SmsProxy(smsInterface);
        //proxy.send("java");

        //动态代理方式
        SmsInterface smsInterface = (SmsInterface) ProxyFactory.getProxy(new SmsInterfaceImp());
        smsInterface.send("1234");

    }
}
