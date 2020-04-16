package c.mj.notes.queue.example;

import c.mj.notes.linear.impl.SequentialList;
import c.mj.notes.queue.impl.SeqQueue;

/**
 * 求n以内的素数环
 * @author ChenMJ
 * @version PrimeRing.class, v 0.1 2020/4/11 14:48  Exp$
 */
public class PrimeRing {
    public static void main(String[] args) {
        new PrimeRing(10);
    }
    private PrimeRing(int n){
        //创建一个顺序表存储素数
        SequentialList<Integer> sList = new SequentialList<Integer>(n);
        sList.add(1);

        SeqQueue<Integer> seqQueue = new SeqQueue<Integer>(n);
        for (int i = 2; i <= n; i++) {
            seqQueue.enqueue(i);
        }

        int i = 0;
        while (!seqQueue.isEmpty()){
            int k = seqQueue.dequeue();
            //判断是否为素数
            if (isPrime(sList.get(i)+k)){
                i++;
                sList.add(k);
            }else{
                seqQueue.enqueue(k);
            }
        }
        System.out.println("素数环："+sList.toString());
    }

    private boolean isPrime(int k){
        if (k == 2){
            return true;
        }
        if (k < 2 || k % 2 == 0){
            return false;
        }
        int j = (int) Math.sqrt(k);
        if (j % 2 == 0){
            j--;
        }
        while (j>2 && k%j != 0){
            j-=2;
        }
        return j<2;
    }
}
