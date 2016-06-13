package com.xxhwap.webservice;
import com.xxhwap.webservice.impl.SendArticleMsgWebServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by Administrator on 2016/5/27.
 */
public class ServiceMain {
    public static void main(String[] args) {
        String address = "http://localhost:8080/hb";
        Endpoint.publish(address, new SendArticleMsgWebServiceImpl());
        System.out.println("发布消息成功");
    }

}
