package com.lish.demo.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者模型
 *
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-11
 */
public class TestConsumer {

    public static AtomicInteger produceCount = new AtomicInteger(0);

    public static final int BUFFER_SIZE = 10;

    // 产品模型
    public static Queue<String> produces = new LinkedList<>();

    public static WhileLock produceLock = new WhileLock();
    public static WhileLock consumerLock = new WhileLock();

    static class Producer extends Thread {
        @Override
        public void run() {
            while(true){
                // 查看是否需要进入挂起状态
                if(produceCount.get() == BUFFER_SIZE){
                    produceLock.sleep();
                }

                // 生产资源
                produceCount.getAndIncrement();
                String produce = Thread.currentThread().toString() + System.nanoTime();
                System.out.println("\u001b[31;42mProduce："+produce + " " + produceCount +" \u001b[0m");
                produces.add(produce);

                // 当开始生产时通知消费线程
                if(produceCount.get() > 0){
                    consumerLock.wakeup();
                }
            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                // 检测缓存中是否还有可消费数据
                if (produceCount.get() == 0) {
                    consumerLock.sleep();
                }

                // 消费资源
                String remove = produces.remove();
                System.out.println("\u001b[32;41mConsumer：" + remove + " " + produceCount + "\u001b[0m");
                produceCount.getAndDecrement();

                // 当开始消费的时候唤醒生产者
                if (produceCount.get() == BUFFER_SIZE - 1) {
                    produceLock.wakeup();
                }
            }
        }
    }

    static class WhileLock{

        private volatile boolean single = true;

        public void sleep(){
            single = true;
            while(single){
                // 忙休眠
            }
        }

        public void wakeup(){
            single = false;
        }
    }

    static void fewSleep(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        producer.start();
        fewSleep();
        consumer.start();
    }

}
