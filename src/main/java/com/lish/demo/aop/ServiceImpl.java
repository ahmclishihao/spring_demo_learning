package com.lish.demo.aop;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 业务实现
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-8
 */
@Service
public class ServiceImpl extends ServiceAbstract implements ServiceInterface {

    public BigDecimal pay(BigDecimal addMoney) {
        addMoney.setScale(2);
        System.out.printf("老王账户： %s \n",addMoney.toString());
        return addMoney;
    }
}
