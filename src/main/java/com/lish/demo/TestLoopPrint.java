package com.lish.demo;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-30
 */
public class TestLoopPrint {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        A a1 = new A();
        B b = new B();
        B b2 = new B();

        a.start();
        b.start();
        a1.start();
        b2.start();

    }

    static class A extends Thread {
        @Override
        public void run() {
            while (true) {
                for (int i = 1; i < 11; i++) {
                    lock.lock();
                    fewSleep();
                    System.out.print("\u001b[31m" + i % 10 +"\u001b[0m");
                    lock.unlock();
                }
            }
        }
    }

    static class B extends Thread {
        @Override
        public void run() {
            while(true){
                for (int i = 0; i < 26; i++) {
                    lock.lock();
                    fewSleep();
                    System.out.print("\u001b[32m" +(char)('A'+i)+"\u001b[0m");
                    lock.unlock();
                }
            }
        }


        public List<String> getList(String id){
            return null;
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
