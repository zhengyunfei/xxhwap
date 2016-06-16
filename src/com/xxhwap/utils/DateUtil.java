package com.xxhwap.utils;

import java.text.DateFormat;
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
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static void main(String args[]){
        String time=getBeforeNDaysTime(2);
        System.out.println(time);
    }

}
