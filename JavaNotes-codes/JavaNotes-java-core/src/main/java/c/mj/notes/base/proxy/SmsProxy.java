package c.mj.notes.base.proxy;

/**
 * 静态代理实现
 *
 * @author admin
 * @version 1.0.0
 * @ClassName SmsProxy.java
 * @Description TODO
 * @createTime 2021年05月20日 15:51:00
 */
public class SmsProxy implements SmsInterface {

    private final SmsInterface smsInterface;

    public SmsProxy(SmsInterface smsInterface) {
        this.smsInterface = smsInterface;
    }

    @Override
    public String send(String message) {
        System.out.println("before method send()");

        smsInterface.send(message);
        System.out.println("after method send()");

        return message;
    }
}
