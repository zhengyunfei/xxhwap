package com.xxhwap.springQuartz;

import com.xxhwap.dao.book.impl.BookDaoImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/13.
 */
public class AutoDeleteOf7Days{
    private BookDaoImpl bookDao;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map properties = context.getMergedJobDataMap();
        System.out.println("Hello World!");
        System.out.println("Previous Fire Time: "+context.getPreviousFireTime());//上次触发任务的时间
        System.out.println("Current Fire Time: "+context.getFireTime());//当然触发时间
        System.out.println("Next Fire Time: "+context.getNextFireTime());//下次触发时间
        System.out.println(properties.get("message"));
        System.out.println();
    }
    public void delete() throws JobExecutionException {
        //first get today time
        String day=getBefore7DaysTime();
        //delete database sale-time<day
        Map<String,Object> queryMap=new HashMap<String, Object>();
        queryMap.put("time",day);
        try{
            int count=bookDao.deleteByMap(queryMap);
            System.out.println("定时任务删除了。。。。。。。。。。。。。。。。。。。。"+count+"条数据");
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    /**
     * 7 day before current time
     */
    public  String getBefore7DaysTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        long time = (date.getTime() / 1000) - 60 * 60 * 24*7;
        date.setTime(time*1000);
        return sdf.format(date);
    }
    public void setBookDao(BookDaoImpl bookDao) {
        this.bookDao = bookDao;
    }

    public BookDaoImpl getBookDao() {
        return bookDao;
    }
    public static void main(String args []){
        AutoDeleteOf7Days t=new AutoDeleteOf7Days();
        String time=t.getBefore7DaysTime();
        System.out.println(time);
    }
}
