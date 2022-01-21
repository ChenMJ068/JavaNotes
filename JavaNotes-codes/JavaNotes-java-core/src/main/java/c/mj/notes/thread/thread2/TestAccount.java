package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create class TestAccount.java @version 1.0.0 by @author ChenMJ @date 2022-01-19 10:20:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class TestAccount {
    public static void main(String[] args) {
        Account account = new AccountCas(10000);
        Account.demo(account);
    }
}

/**
 * 不需要加锁就能保护共享资源
 */
class AccountCas implements Account{

    private AtomicInteger balance;

    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while (true){
            int prev = balance.get();
            int next = prev - amount;
            if (balance.compareAndSet(prev,next)){
                break;
            }
        }
    }
}

/**
 * 线程不安全，不能保护共享变量，需要加锁才可以
 */
class AccountUnsafe implements Account{

    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        synchronized (this){
            return this.balance;
        }

    }

    @Override
    public void withdraw(Integer amount) {
        synchronized (this){
            this.balance -= amount;
        }
    }
}

interface Account{
    //获取余额
    Integer getBalance();
    //取款
    void withdraw(Integer amount);

    static void demo(Account account){
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(()->{
                account.withdraw(10);
            }));
        }

        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance()+" cost:"+(end-start)/1000_00+" ms");
    }
}