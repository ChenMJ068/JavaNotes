package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create class Test5.java @version 1.0.0 by @author ChenMJ @date 2022-01-12 14:16:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new People().start();
        }

        Thread.sleep(2000);
        for (Integer id: Mailboxes.getIds()) {
            new Postman(id,"你的信编号是："+id).start();
        }
    }
}

@Slf4j(topic = "C.MJ.People")
class People extends Thread{
    @Override
    public void run() {
        GuardedObject go = Mailboxes.createGuardedObject();
        log.debug("开始收信id:{}", go.getId());
        Object mail = go.get(5000);
        log.debug("收到信id:{}，内容:{}", go.getId(),mail);
    }
}

@Slf4j(topic = "C.MJ.Postman")
class Postman extends Thread{
    private int id;
    private String mail;

    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.getGuardedObject(id);
        log.debug("开始送信i d:{}，内容:{}", guardedObject.getId(),mail);
        guardedObject.complete(mail);
    }

    public Postman(int id,String mail){
        this.id = id;
        this.mail = mail;
    }
}

class Mailboxes{
    private static ConcurrentHashMap<Integer,GuardedObject> boxs = new ConcurrentHashMap<>();
    private static int id = 1;

    //产生唯一的对象
    private static synchronized int generateId(){
        return id++;
    }

    public static GuardedObject getGuardedObject(int id){
        return boxs.remove(id);
    }


    public static GuardedObject createGuardedObject(){
        GuardedObject go = new GuardedObject(generateId());
        boxs.put(go.getId(),go);
        return go;
    }

    public static Set<Integer> getIds(){
        return boxs.keySet();
    }

}
