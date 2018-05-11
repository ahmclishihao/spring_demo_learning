package com.lish.demo.thread;

import java.util.concurrent.Semaphore;

/**
 * 测试信号灯控制的生产者和消费者
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-11
 */
public class TestConsumerSemaphore {

    Semaphore fillSingle = new Semaphore(0);
    Semaphore emptySingle = new Semaphore(10);

    public static void main(String[] args) {

    }


}
