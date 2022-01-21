package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

/**
 * create class Test13.java @version 1.0.0 by @author ChenMJ @date 2022-01-18 15:57:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test13 {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();

        Thread.sleep(3500);
        log.debug("停止监控");
        tpt.stop();
    }
}
@Slf4j(topic = "C.MJ.TwoPhaseTermination")
class TwoPhaseTermination{
    private Thread monitorThread;
    private boolean stop = false;
    private boolean starting = false;

    public void start(){
        if (starting){
            return;
        }
        starting = true;
        monitorThread = new Thread(()->{
            while(true){
                Thread current = Thread.currentThread();
                if (stop){
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }

        },"monitor");
        monitorThread.start();

    }
    public void stop(){
        stop = true;
        monitorThread.interrupt();
    }
}