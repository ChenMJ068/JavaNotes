package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 原子更新器
 * create class Test2.java @version 1.0.0 by @author ChenMJ @date 2022-01-21 10:25:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test34 {
    public static void main(String[] args) {
        Student stu = new Student();
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(Student.class,String.class,"name");
        boolean flag = updater.compareAndSet(stu, null, "张三");

        log.debug("{},result:{}",stu.toString(),flag);
    }
}

class Student{
    volatile String name;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}