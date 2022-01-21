package c.mj.notes.thread.thread3;

/**
 * create class TestFinal.java @version 1.0.0 by @author ChenMJ @date 2022-01-21 16:27:00
 */
public class TestFinal {
    final static int A = 10;
    final static int B = Short.MAX_VALUE+1;

    final int a = 20;
    final int b = Integer.MAX_VALUE;

    final void test1(){
        System.out.println("final method");
    }
}

class UseFinal1{
    public void test(){
        System.out.println(TestFinal.A);
        System.out.println(TestFinal.B);
        System.out.println(new TestFinal().a);
        System.out.println(new TestFinal().b);

        new TestFinal().test1();
    }
}

class UseFinal2{
    public void test(){
        System.out.println(TestFinal.A);
    }
}

