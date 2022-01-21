package c.mj.notes.thread.thread3;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create class TestPool.java @version 1.0.0 by @author ChenMJ @date 2022-01-21 16:51:00
 */
@Slf4j(topic = "C.MJ.TestPool")
public class TestPool {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(1,1,1000,TimeUnit.MILLISECONDS,(queue,task)->{

            //1.死等；
            //queue.put(task);
            // 2.带超时时间等待；
            //queue.offer(task,500,TimeUnit.MILLISECONDS);
            // 3.让调用者放弃任务执行；
            //log.debug("队列已满，放弃执行{}",task);
            // 4.让调用者抛出异常；
            //throw new RuntimeException("执行任务失败"+task);
            // 5.让调用者自己执行任务
            task.run();
        });
        for (int i = 0; i < 4; i++) {
            int j = i;
            pool.execute(()->{
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}",j);
            });
        }
    }
}

@FunctionalInterface //拒绝策略
interface RejectPolicy<T>{
    void reject(BlockingQueue queue,T task);
}

@Slf4j(topic = "C.MJ.ThreadPool")
class ThreadPool{
    //任务队列
    private BlockingQueue<Runnable> taskQueue;

    //线程集合,共享变量线程不安全，需要加锁保护
    private HashSet<Worker> workers = new HashSet();

    //核心线程数
    private int coreSize;

    //获取任务的超时时间
    private long timeout;

    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    public void execute(Runnable task){
        //当线程任务数没有超过coreSize时，直接交给worker对象执行；如果超过时，加入任务队列暂存
        synchronized (workers){
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增 worker{},{}",worker,task);
                workers.add(worker);
                worker.start();
            }else{
                //log.debug("加入任务队列{}...",task);
//                taskQueue.put(task);
                //队列满时，可以有以下场景处理：1.死等；2.带超时时间等待；3.让调用者放弃任务执行；4.让调用者抛出异常；5.让调用者自己执行任务
                //将以上多种可能交给调用者来决定，使用哪种方式来执行任务
                taskQueue.tryPut(rejectPolicy,task);
            }
        }
    }

    public ThreadPool(int taskQueue, int coreSize, long timeout, TimeUnit timeUnit,RejectPolicy<Runnable> rejectPolicy) {
        this.taskQueue = new BlockingQueue<>(taskQueue);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }

    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务：
            // 1.当task不为null时，执行任务；
            // 2.当task执行完毕，在接着从任务队列获取任务并执行
//            while (task != null || (task = taskQueue.take()) != null){ //无超时等待
            while (task != null || (task = taskQueue.poll(timeout,timeUnit)) != null){
                try{
                    log.debug("正在执行...{}",task);
                    task.run();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    task = null;
                }
            }

            //线程执行完成后从workers中移除任务
            synchronized (workers){
                log.debug("结束使命，被移除的是 {}",this);
                workers.remove(this);
            }
        }
    }
}
//自定义拒绝策略
@Slf4j(topic = "C.MJ.BlockingQueue")
class BlockingQueue<T>{
    //1.任务队列
    private Deque<T> queue = new ArrayDeque<>();

    //2.锁保护deque中元素
    private ReentrantLock lock = new ReentrantLock();

    //3.生产者阻塞的条件变量
    private Condition fullWaitSet = lock.newCondition();

    //4.消费者阻塞的条件变量
    private Condition emptyWaitSet = lock.newCondition();

    //5.队列的容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    //带超时的阻塞获取
    public T poll(long timeout, TimeUnit unit){
        lock.lock();
        try{
            //将时间统一转换为纳秒
            long nanos = unit.toNanos(timeout);

            while (queue.isEmpty()){
                try {
                    if (nanos <= 0) return null;
                    nanos = emptyWaitSet.awaitNanos(nanos);//这个等待是超时等待，需要添加一个超时等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();//获取队列中第一个元素
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //阻塞获取
    public T take(){
        lock.lock();
        try{
            while (queue.isEmpty()){
                try {
                    emptyWaitSet.await();//这个等待是超时等待，需要添加一个超时等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();//获取队列中第一个元素
            fullWaitSet.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T task){
        lock.lock();
        try{
            while (queue.size() == capacity) {
                fullWaitSet.await();
            }
            log.debug("等待加入任务队列{}...",task);
            queue.addLast(task);//队列尾部加入新元素
            emptyWaitSet.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //带超时的阻塞添加
    public boolean offer(T task,long timeout,TimeUnit timeUnit){
        lock.lock();
        try{
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                nanos = fullWaitSet.awaitNanos(nanos);
            }
            log.debug("等待加入任务队列{}...",task);
            if (nanos <= 0) {
                return false;
            }
            queue.addLast(task);//队列尾部加入新元素
            emptyWaitSet.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return true;
    }

    //获取大小
    public int size(){
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try{
            //判断队列已满
            if (queue.size() == capacity) {
                rejectPolicy.reject(this,task);
            }
            //队列有空闲
            else {
                log.debug("method tryPut()...加入任务队列{}",task);
                queue.addLast(task);//队列尾部加入新元素
                emptyWaitSet.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}