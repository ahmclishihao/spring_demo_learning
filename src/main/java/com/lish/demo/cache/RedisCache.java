package com.lish.demo.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-31
 */
@Component
public class RedisCache {

    @Cacheable(value = "getList",key = "#keyKey")
    public List<Long> getList(String keyKey){
        LinkedList<Long> datas = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            long l = System.nanoTime();
            System.out.println(keyKey + l);
            datas.add(l);
            fewSleep();
        }

        return datas;
    }







    public static void fewSleep(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
