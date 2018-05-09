package com.lish.demo;

import com.lish.demo.aspect.NotVeryUsefulAspect;
import com.lish.demo.aspect.demo.UserService;
import com.lish.demo.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-8
 */
public class TestParent {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring.xml");

        UserService userService = applicationContext.getBean(UserService.class);
        User user = userService.getUser(1L);
        User user1 = userService.addUser(null);
        User user2 = userService.modifyUser(null);
        boolean delUser = userService.delUser(null);

        boolean proxyClass = Proxy.isProxyClass(userService.getClass());

    }

}
