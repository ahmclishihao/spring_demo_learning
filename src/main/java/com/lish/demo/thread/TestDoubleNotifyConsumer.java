package com.lish.demo.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用notify和wait控制同步线程
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-11
 */
public class TestDoubleNotifyConsumer {

    private static Object PRODUCER_LOCK = new Object();
    private static Object CONSUMER_LOCK = new Object();

    private static AtomicInteger resourceCount = new AtomicInteger(0);

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
            try {
                while (true) {
                    synchronized (PRODUCER_LOCK) {
                        // 是否已经充足
                        while (resourceCount.get() == BUFFER_SIZE) {
                            PRODUCER_LOCK.wait();
                        }
                        // 添加资源
                        process();

                        // 唤醒其他的生产者
                        PRODUCER_LOCK.notifyAll();
                    }
                    if(resourceCount.get() > 0 ){
                        synchronized (CONSUMER_LOCK) {
                            CONSUMER_LOCK.notifyAll();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void process(){
            fewSleep();
            String resource = Thread.currentThread().toString() + " " + System.nanoTime();
            System.out.println("\u001b[33;44mProducer:"+resource + " "+resourceCount+"\u001b[0m");
            resources.add(resource);
            resourceCount.getAndIncrement();
        }

    }

    static class Consumer extends Thread {

        public Consumer(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (CONSUMER_LOCK) {
                        // 是否已经消费完
                        while (resourceCount.get() == 0) {
                            CONSUMER_LOCK.wait();
                        }
                        // 消费资源
                        process();

                        // 唤醒生产者
                        CONSUMER_LOCK.notifyAll();
                    }

                    if(resourceCount.get() < BUFFER_SIZE){
                        synchronized (PRODUCER_LOCK) {
                            PRODUCER_LOCK.notifyAll();
                        }
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void process(){
            fewSleep();
            Object remove = resources.remove();
            resourceCount.getAndDecrement();
            System.out.println("\u001b[34;43m"+Thread.currentThread()+":"+remove  + " "+resourceCount + "\u001b[0m");
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
