package com.xxhwap.webservice.client;

import com.xxhwap.webservice.ISendArticleMsgWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/5/27.
 */
public class Client {
    /**
     * @param args
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {
        // TODO Auto-generated method stub
        //创建访问wsdl服务地址的url
        URL url = new URL("http://localhost:8080/hb?wsdl");
        //通过Qname指明服务的具体信息
        /* * 第一个参数：接口的包名称，反序
         * 第二个参数：实现类名+Service
         * */
        QName qname = new QName("http://impl.webservice.xxhwap.com/","SendArticleMsgWebServiceImplService");
        //创建服务
        Service service =  Service.create(url, qname);
        //实现接口
        ISendArticleMsgWebService iservice = service.getPort(ISendArticleMsgWebService.class);
        //以上服务有问题，依然依赖于IMyServie接口
        iservice.send("huangbiao");

    }

}
