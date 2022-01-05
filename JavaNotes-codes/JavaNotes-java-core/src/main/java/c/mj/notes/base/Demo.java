package c.mj.notes.base;

/**
 * create class Demo.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 10:40:00
 */
public class Demo {
    public static void main(String[] args) {
        change("021");
        change("022");
        change("023");
    }

    public static void change(String msgtp){
        switch (msgtp){
            case "021":
                System.out.println("---021---");
                break;
            case "022" :
                System.out.println("---022---");
                break;
            default:
                System.out.println("无效类型");
                break;
        }
    }
}
