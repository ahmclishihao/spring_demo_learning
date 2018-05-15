package com.lish.demo.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 使用阻塞队列 实现生产者消费者
 *
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-11
 */
public class TestBlockingQueue {

    private static BlockingQueue resources = new LinkedBlockingQueue(10);

    static class Producer extends Thread {
        public Producer(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String resource = Thread.currentThread().toString() + System.nanoTime();
                    System.out.println("\u001b[33;44mProducer:" + resource + " " + "\u001b[0m");
                    resources.put(resource);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                try {
                    Object remove = resources.take();
                    System.out.println("\u001b[34;43m" + Thread.currentThread() + ":" + remove + " " + "\u001b[0m");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
