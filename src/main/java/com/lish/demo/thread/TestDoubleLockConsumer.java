package com.lish.demo.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用同步锁控制线程
 * 双Lock重入锁，Condition内置监视器，生产者和消费者各有一个锁，让入队的同时可以出队，这种实现方式会有无法出队的问题
 *
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-11
 */
public class TestDoubleLockConsumer {

    // 消费者 锁
    private static Lock CONSUMER_LOCK = new ReentrantLock();
    // 生产者 锁
    private static Lock PRODUCER_LOCK = new ReentrantLock();

    // 资源为空的条件
    private static Condition EMPTY_CON = CONSUMER_LOCK.newCondition();
    // 资源已满的条件
    private static Condition FILL_CON = PRODUCER_LOCK.newCondition();

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
                // 生产者中只有一个人可以生产
                try {
                    PRODUCER_LOCK.lockInterruptibly();
                    while (resCount.get() == BUFFER_SIZE) {
                        FILL_CON.await();
                    }
                    process();
                    if (resCount.get() < BUFFER_SIZE) {
                        FILL_CON.signalAll(); // 通知其他生产者进行生产
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    PRODUCER_LOCK.unlock();
                }

                // 为消费者检测是否可以消费
                if (resCount.get() > 0) {
                    try {
                        CONSUMER_LOCK.lockInterruptibly();
                        EMPTY_CON.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        CONSUMER_LOCK.unlock();

                    }
                }
            }
        }

        private void process() {
            fewSleep();
            String resource = Thread.currentThread().toString() + " " + System.nanoTime();
            System.out.println("\u001b[33;44mProducer:" + resource + " " + resCount + "\u001b[0m");
            resources.offer(resource);
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
                // 消费者中只有一个人可以消费
                try {
                    CONSUMER_LOCK.lockInterruptibly();
                    while (resCount.get() == 0) {
                        EMPTY_CON.await();
                    }
                    process();
                    if (resCount.get() > 0) {
                        EMPTY_CON.signalAll(); // 通知其他消费进行消费
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    CONSUMER_LOCK.unlock();
                }

                // 为生产者检测是否可以继续生产
                if (resCount.get() < BUFFER_SIZE) {
                    try {
                        PRODUCER_LOCK.lockInterruptibly();
                        FILL_CON.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        PRODUCER_LOCK.unlock();
                    }
                }
            }
        }

        public void process() {
            fewSleep();
            Object remove = resources.poll();
            if (remove != null) {
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


}
