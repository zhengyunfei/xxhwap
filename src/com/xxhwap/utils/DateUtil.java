package com.xxhwap.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/16.
 */
public class DateUtil {
    /**
     * n day before current time
     */
    public static   String getBeforeNDaysTime(int n){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        long time = (date.getTime() / 1000) - 60 * 60 * 24*n;
        date.setTime(time*1000);
        return sdf.format(date);
    }
    public static void main(String args[]){
        String time=getBeforeNDaysTime(2);
        System.out.println(time);
    }

}
