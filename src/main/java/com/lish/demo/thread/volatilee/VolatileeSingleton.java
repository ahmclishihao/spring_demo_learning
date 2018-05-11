package com.lish.demo.thread.volatilee;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-10
 */
public class VolatileeSingleton {

    private static volatile VolatileeSingleton instance;

    private VolatileeSingleton() {
    }

    public static VolatileeSingleton getInstance(){
        if(instance == null){
            synchronized(VolatileeSingleton.class){
                if(instance == null){
                    VolatileeSingleton.instance = new VolatileeSingleton();
                }
            }
        }
        return VolatileeSingleton.instance;
    }

}
