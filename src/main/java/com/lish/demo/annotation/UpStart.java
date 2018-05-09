package com.lish.demo.annotation;

import com.lish.demo.annotation.pojo.TestUser;
import com.lish.demo.aop.ServiceInterface;
import com.lish.demo.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * spring 基于注解的配置类
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-9
 */
public class UpStart {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.lish.demo.annotation.config");
        applicationContext.refresh();

        Object oneUser = applicationContext.getBean("oneUser");
        Object service = applicationContext.getBean(ServiceInterface.class);
        TestUser testUser = applicationContext.getBean(TestUser.class);

        List<User> oneList = new LinkedList<>();

        List<Map<String, Object>> finalResult = oneList.stream().map(user -> {
            Map<String, Object> result = new LinkedHashMap<>();
            String name = user.getName();
            result.put("name", name);
            return result;
        }).collect(Collectors.toList());


    }

}
