package com.lish.demo.thread.volatilee;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-10
 */
public class TestV {

    private volatile int i = 0;

    private Lock lock = new ReentrantLock();

    public void increase(){
        // 使用lock锁住后可扩大cpu执行的原子性
        lock.lock();
        // 操作并非原子性 先读 后设置
        i = i + 1;
        lock.unlock();
    }

    // 使用原子级数据操作对象
    private AtomicInteger atomicI = new AtomicInteger();

    public void increasee(){
        atomicI.getAndIncrement();
    }

    public static void main(String[] args) {

        TestV testV = new TestV();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    testV.increasee();
                }
            }).start();
        }

        while(Thread.activeCount() > 1){
            Thread.yield();
        }

        System.out.println(testV.atomicI);
    }

}
