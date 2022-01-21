package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * create class Test6.java @version 1.0.0 by @author ChenMJ @date 2022-01-12 14:58:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test6 {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);


        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(()->{
                queue.put(new Message(id,"值"+id));
            },"生产者"+i).start();
        }

        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = queue.take();
                //log.debug("被消费的消息是：{}",message);
            }


        },"消费者").start();
    }
}
//消息队列类。java线程之间传递
@Slf4j(topic = "C.MJ.MessageQueue")
class MessageQueue{
    private LinkedList<Message> list = new LinkedList<>();
    private int capcityl; //容器的容量上限

    public MessageQueue(int capcityl) {
        this.capcityl = capcityl;
    }

    //获取消息
    public Message take(){
        synchronized (list){
            while (list.isEmpty()){
                try {
                    log.debug("队列为null,消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //从队列头部取
            Message message = list.removeFirst();
            log.debug("已消费消息:{}",message.toString());
            list.notifyAll();
            return message;
        }
    }

    public void put(Message message){
        synchronized (list){
            while (list.size() == capcityl){
                try {
                    log.debug("队列已满，生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //将消息放入尾部
            list.addLast(message);
            log.debug("以生产消息：{}",message.toString());
            list.notifyAll();
        }
    }

}

final class Message{
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", value=" + value + '}';
    }
}