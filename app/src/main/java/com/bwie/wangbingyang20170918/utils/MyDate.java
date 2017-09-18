package com.bwie.wangbingyang20170918.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：王兵洋  2017/9/18 10:13
 * 类的用途： 获取时间的一个类
 */
public class MyDate {

    public static String getFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(System.currentTimeMillis()));
        return date;
    }

    public static String getDateEN() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = format1.format(new Date(System.currentTimeMillis()));
        return date1;
    }

}
