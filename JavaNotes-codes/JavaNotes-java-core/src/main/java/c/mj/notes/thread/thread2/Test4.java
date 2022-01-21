package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * create class Test4.java @version 1.0.0 by @author ChenMJ @date 2022-01-12 10:39:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test4 {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(()->{
            log.debug("等待结果");
            Object obj = guardedObject.get(2000);
            log.debug("结果对象 :{}",obj);
        },"t1").start();

        new Thread(()->{
            log.debug("执行下载");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guardedObject.complete(new Object());
        },"t2").start();
    }
}

@Slf4j(topic = "C.MJ.NOTES")
class GuardedObject{

    private int id;

    public GuardedObject() {
    }

    public GuardedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Object response;

    public Object get(long timeout) {
       synchronized (this){
           long begin = System.currentTimeMillis();
           long passedTime = 0;
           while (response == null){
               long waitTime = timeout - passedTime;
               //执行时间超过超时等待时间退出
               if (waitTime <= 0){
                   log.debug("break.....");
                   break;
               }
               try {

                   this.wait(waitTime);//避免虚假唤醒
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               passedTime = System.currentTimeMillis() - begin;
           }
          return response;
       }

    }

    public void complete(Object response){
        synchronized (this){

            this.response = response;
            this.notifyAll();
        }
    }
}

