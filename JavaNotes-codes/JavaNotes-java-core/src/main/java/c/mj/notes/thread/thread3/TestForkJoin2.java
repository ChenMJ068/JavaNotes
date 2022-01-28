package c.mj.notes.thread.thread3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * create class TestForkJoin2.java @version 1.0.0 by @author ChenMJ @date 2022-01-25 10:17:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class TestForkJoin2 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        log.debug("{}",pool.invoke(new MyTask(5)));
    }
}
@Slf4j(topic = "C.MJ.MyTask")
class MyTask extends RecursiveTask<Integer> {

    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {

        if (n == 1) {
            log.debug("join {}",n);
            return 1;
        }
        MyTask task = new MyTask(n - 1);
        task.fork();//让一个线程去执行任务
        log.debug("fork {} + {}",n,task);

        int result = n + task.join();
        log.debug("join() {} + {} = {}",n,task,result);
        return result;
    }

    @Override
    public String toString() {
        return "MyTask{" +"n=" + n +'}';
    }
}