package com.lish.demo.thread.volatilee;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-10
 */
public class TestVS {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new TestThread().start();
        }
    }

    static class TestThread extends Thread{
        @Override
        public void run() {
            VolatileeSingleton instance = VolatileeSingleton.getInstance();
            System.out.println(instance.toString());
        }
    }

}
