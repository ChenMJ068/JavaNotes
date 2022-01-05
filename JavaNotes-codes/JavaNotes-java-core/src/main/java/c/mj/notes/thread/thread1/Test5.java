package c.mj.notes.thread.thread1;

import lombok.extern.slf4j.Slf4j;
import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

/**
 * create class Test5.java @version 1.0.0 by @author ChenMJ @date 2021-12-31 20:39:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() ->{
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    break;
                }
            }
            log.debug("结束");
        },"t1");
        thread.start();

        Thread.sleep(1000);
        log.debug("end");
    }

}
