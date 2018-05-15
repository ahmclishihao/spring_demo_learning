package com.lish.demo.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用yield无法实现
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-11
 */
public class TestYield {

    public static Queue resources = new LinkedList();

    public static AtomicInteger resCount = new AtomicInteger(0);

    public static final int BUFFER_SIZE = 10;

    static class Producer extends Thread {
        public Producer(String name) {
            super(name);
        }

        @Override
        public void run() {

            while (true) {
                while (resCount.get() > BUFFER_SIZE) {
                    Thread.yield();
                }

                // 生产资源
                fewSleep();
                String resource = Thread.currentThread().toString() + System.nanoTime();
                System.out.println("\u001b[35;46mProducer:" + resource + " " + resCount + "\u001b[0m");
                resCount.getAndIncrement();
                resources.offer(resource);
            }
        }
    }

    static class Consumer extends Thread {
        public Consumer(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                while (resCount.get() < 0) {
                    Thread.yield();
                }
                // 消费资源
                fewSleep();
                Object remove = resources.poll();
                resCount.getAndDecrement();
                System.out.println("\u001b[34;43m" + Thread.currentThread() + ":" + remove + " " + resCount + "\u001b[0m");
            }
        }
    }

    public static void fewSleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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

}
