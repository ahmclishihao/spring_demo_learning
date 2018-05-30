package com.lish.demo;

import java.io.IOException;

/**
 * 将目标的文件夹中的所有文件复制到另一个文件夹下
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-30
 */
public class TestDirCopy {

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("cp -r /home/lish/temp/nginx_www /home/lish/temp/nginx_www_backup1");
    }

}
