package com.lish.demo.annotation.config;

import com.lish.demo.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;

/**
 * 注解配置类
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-9
 */
@Configuration
@ComponentScans({
@ComponentScan("com.lish.demo.annotation.pojo")
})
public class ConfigOne {

    @Bean("oneUser")
    public User getUser(){
        LinkedList<String> somethings = new LinkedList<String>();
        somethings.add("apple");
        somethings.add("orange");
        somethings.add("banner");

        User user = new User();
        user.setName("123");
        user.setAge(123);
        user.setList(somethings);

        return user;
    }

}
