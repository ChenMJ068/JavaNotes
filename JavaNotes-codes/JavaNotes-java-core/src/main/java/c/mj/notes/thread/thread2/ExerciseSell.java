package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ChenMJ
 * @version ExerciseSell.class, v 0.1 2022/1/5 20:59 n-cz Exp$
 */
@Slf4j(topic = "C.MJ.NOTES")
public class ExerciseSell {
    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow(2000);
        List<Thread> list = new ArrayList<>();
        List<Integer> sellCount = new ArrayList<>();

        for (int i=0;i<2000;i++){
            Thread t = new Thread(()->{
                int count = ticketWindow.sell(randomAmount());
                sellCount.add(count);
            });
            list.add(t);
            t.start();
        }

        list.forEach((t) ->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        log.debug("卖出的票:{}",sellCount.stream().mapToInt(i->i).sum());
        log.debug("余票:{}",ticketWindow.getCount());
    }

    static Random random = new Random();

    //随机数1~5
    public static int randomAmount(){
        return random.nextInt(5) + 1;
    }
}
//售票窗口
class TicketWindow{
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
    //售票
    public int sell(int count){
        if (this.count >= count){
            this.count -= count;
            return count;
        }else {
            return 0;
        }
    }
}