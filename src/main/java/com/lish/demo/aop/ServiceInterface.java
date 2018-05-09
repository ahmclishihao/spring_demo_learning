package com.lish.demo.aop;

import java.math.BigDecimal;

/**
 * 业务接口
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-8
 */
public interface ServiceInterface {

    Integer NAME = 111;

    String SELF = "ServiceInterface";

    /**
     * 账户入账
     * @param addMoney
     * @return
     */
    BigDecimal pay(BigDecimal addMoney);

}
