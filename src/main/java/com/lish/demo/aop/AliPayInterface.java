package com.lish.demo.aop;

import java.math.BigDecimal;

/**
 * 不同接口重复的方法
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-8
 */
public interface AliPayInterface {

    String NAME = "AliPay";

    /**
     * 账户入账
     * @param addMoney
     * @return
     */
    BigDecimal pay(BigDecimal addMoney);

}
