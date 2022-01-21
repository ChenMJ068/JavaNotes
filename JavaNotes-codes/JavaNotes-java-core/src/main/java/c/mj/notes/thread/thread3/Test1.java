package c.mj.notes.thread.thread3;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * create class Test1.java @version 1.0.0 by @author ChenMJ @date 2022-01-21 15:14:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test1 {
    public static void main(String[] args) {
        //unsafeFormat();
        safeFormat();
    }

    private static void safeFormat(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                log.debug("{}",dtf.parse("1951-04-21"));
            }).start();
        }
    }

    private static void unsafeFormat(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
//                synchronized (sdf){
                    try {
                        log.debug("{}",sdf.parse("1951-04-21"));
                    } catch (ParseException e) {
                        log.error("{}",e);
                    }
 //               }
            }).start();
        }
    }
}