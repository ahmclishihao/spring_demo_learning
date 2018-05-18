package com.lish.demo.classloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-18
 */
public class CustomLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = new byte[2048];
        try{
            FileInputStream fileInputStream = new FileInputStream("/home/lish/temp/servlet/TestSystemLoader.class");

            int len = fileInputStream.read(classBytes);
            return defineClass(name,classBytes,0,len);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
