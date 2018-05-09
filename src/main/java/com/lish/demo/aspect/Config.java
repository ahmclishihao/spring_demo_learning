package com.lish.demo.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * aspectj基于注解的配置
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-9
 */
@Configuration
@ComponentScan("com.lish.demo.aspect.*")
@EnableAspectJAutoProxy
public class Config {

    /**
     * 注入切面
     */
    @Bean
    public NotVeryUsefulAspect getNotVeryUsefulAspect(){
        return new NotVeryUsefulAspect();
    }

}
