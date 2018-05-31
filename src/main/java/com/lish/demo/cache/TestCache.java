package com.lish.demo.cache;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-31
 */
public class TestCache {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.start();
        RedisCache bean = applicationContext.getBean(RedisCache.class);
        bean.getList("1");
        bean.getList("1");
        bean.getList("2");
        bean.getList("2");


    }


}
