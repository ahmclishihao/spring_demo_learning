package com.lish.demo.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用同步锁控制线程
 * Lock重入锁，Condition内置监视器
 *
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-11
 */
public class TestLockConsumer {

    // 可重入锁
    private static Lock lock = new ReentrantLock();

    private static Condition fillCondition = lock.newCondition();
    private static Condition emptyCondition = lock.newCondition();

    private static AtomicInteger resCount = new AtomicInteger(0);

    private static int BUFFER_SIZE = 10;

    private static Queue resources = new LinkedList();

    public static void main(String[] args) {
        Producer producer = new Producer("Producer 001");
        Producer producer2 = new Producer("Producer 002");
        producer.start();
        producer2.start();

        fewSleep();
        Consumer consumer = new Consumer("Consumer 001");
        Consumer consumer1 = new Consumer("Consumer 002");
        Consumer consumer2 = new Consumer("Consumer 003");

        consumer.start();
        consumer1.start();
        consumer2.start();
    }


    static class Producer extends Thread {

        public Producer(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (resCount.get() == BUFFER_SIZE) {
                        fillCondition.await();
                    }
                    process();
                    emptyCondition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }

        private void process() {
            fewSleep();
            String resource = Thread.currentThread().toString() + " " + System.nanoTime();
            System.out.println("\u001b[33;44mProducer:" + resource + " " + resCount + "\u001b[0m");
            resources.add(resource);
            resCount.getAndIncrement();
        }
    }

    static class Consumer extends Thread {

        public Consumer(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (resCount.get() == 0) {
                        emptyCondition.await();
                    }
                    process();
                    fillCondition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }

        public void process() {
            fewSleep();
            Object remove = resources.remove();
            resCount.getAndDecrement();
            System.out.println("\u001b[34;43m" + Thread.currentThread() + ":" + remove + " " + resCount + "\u001b[0m");
        }
    }


    public static void fewSleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
