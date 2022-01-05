package c.mj.notes.base.proxy;

public class SmsInterfaceImp implements SmsInterface {
    @Override
    public String send(String message) {
        System.out.println("send massage:" + message);
        return message;
    }
}
