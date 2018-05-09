package com.lish.demo.aspect;

import com.lish.demo.aspect.demo.UserService;
import com.lish.demo.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

/**
 * 启动spring
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-9
 */
public class UpStart {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = applicationContext.getBean(UserService.class);

        User user = userService.getUser(1L);
        User user1 = userService.addUser(null);
        User user2 = userService.modifyUser(null);
        boolean delUser = userService.delUser(null);

        boolean proxyClass = Proxy.isProxyClass(userService.getClass());

    }

}
