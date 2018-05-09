package com.lish.demo.aspect.demo;

import com.lish.demo.pojo.User;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-9
 */
public interface UserService {

    User addUser(User user);

    User modifyUser(User user);

    boolean delUser(User user);

    User getUser(Long id);

}
