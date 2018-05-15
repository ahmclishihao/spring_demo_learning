package com.lish.demo.thread.pool;

import java.util.concurrent.*;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-15
 */
public class TestThreadPool {


    public static void main(String[] args) {

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 100, TimeUnit.NANOSECONDS
//              所以如果是个无界的阻塞队列 那么队列可以缓存所有的任务，等待被调用和执行
                , new LinkedBlockingQueue<>(100)
                , threadFactory
                , new ThreadPoolExecutor.AbortPolicy());
        int i = 100;
        while(i-- >0){
            final int j = i;
            executor.execute(()->{
                fewSleep();
                System.out.println(Thread.currentThread().toString()+ " "+j);
            });
        }

        System.out.println("任务发布完毕");

        while(Thread.activeCount() > 1 ){

        }

    }


    public static void fewSleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
