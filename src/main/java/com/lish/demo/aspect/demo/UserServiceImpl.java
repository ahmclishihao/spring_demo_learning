package com.lish.demo.aspect.demo;

import com.lish.demo.aspect.NeedLog;
import com.lish.demo.aspect.NeedPermission;
import com.lish.demo.aspect.NeedTransaction;
import com.lish.demo.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-9
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    @NeedLog("xxx 正在添加用户")
    @NeedPermission(value = {"用户登录权限","添加用户权限"},role = "超级管理")
    public User addUser(User user) {
        System.out.println("添加一个用户");
        return null;
    }

    @Override
    @NeedLog("xxx 正在修改用户")
    public User modifyUser(User user) {
        System.out.println("修改一个用户");
        return null;
    }

    @Override
    @NeedTransaction("repeatable")
    @NeedLog("xxx 正在删除用户")
    public boolean delUser(User user) {
        System.out.println("删除一个用户");
        return true;
    }

    @Override
    @NeedLog("xxx 正在搜索用户")
    public User getUser(Long id) {
        System.out.printf("搜索得到一个用户 %d \n",id);
        User user = new User();
        user.setName("Tom");
        user.setAge(123);
        return user;
    }
}
