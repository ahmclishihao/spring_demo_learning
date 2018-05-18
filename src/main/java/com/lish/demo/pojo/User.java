package com.lish.demo.pojo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-8
 */
@Lazy
@Component("myUser")
@Scope("singleton")
public class User implements InitializingBean,DisposableBean {

    private String name;

    private Integer age;

    public List<String> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("新的用户访问");
    }

    @PreDestroy
    public void destory(){
        System.out.println("这个用户离开了");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("新的用户访问 "+this.hashCode());
    }

    public void destroy() throws Exception {
        System.out.println("这个用户离开了 "+this.hashCode());
    }
}
