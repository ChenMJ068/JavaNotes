package c.mj.notes.thread.thread3;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create class TestCHM.java @version 1.0.0 by @author ChenMJ @date 2022-01-26 16:09:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class TestCHM {
    static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
       int h = 2022;
       int g = 0127;
       log.debug("{}",h & g);
    }
}