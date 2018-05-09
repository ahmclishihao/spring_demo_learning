package com.lish.demo.annotation.config;

import com.lish.demo.aop.ServiceImpl;
import com.lish.demo.aop.ServiceInterface;
import com.lish.demo.aop.ServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注解配置类 02
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-9
 */
@Configuration
public class ConfigTwo {

    @Bean
    public ServiceInterface getService(){
        return new ServiceProxy(new ServiceImpl());
    }

}
