package com.lish.demo.classloader;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-18
 */
public class TestSystemLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        CustomLoader customLoader = new CustomLoader();
        Class<?> aClass = customLoader.findClass("com.lish.demo.classloader.TestSystemLoader");
        TestSystemLoader o = (TestSystemLoader) aClass.newInstance();
        System.out.println(o);
    }




}
