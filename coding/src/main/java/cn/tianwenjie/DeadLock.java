package cn.tianwenjie;

import com.google.common.util.concurrent.Uninterruptibles;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(()->{
            lock1.lock();
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            lock2.lock();

            lock1.unlock();
            lock2.unlock();
        }).start();

        new Thread(() -> {
            lock2.lock();
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            lock1.lock();

            lock2.unlock();
            lock1.unlock();
        }).start();

    }

}
