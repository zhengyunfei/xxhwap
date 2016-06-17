package com.xxhwap.book;
/**
 * Created by Administrator on 2016/5/30.
 */

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveDocumentByURL {

    public RetrieveDocumentByURL(String url) throws ClientProtocolException, IOException{
        URL urlGet = new URL(url);
        HttpURLConnection http = (HttpURLConnection) urlGet
                .openConnection();
        http.setRequestMethod("GET"); // 必须是get方式请求
        http.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        http.setDoOutput(true);
        http.setDoInput(true);
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
        System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
        http.connect();
        InputStream is = http.getInputStream();
        TudouBookInfo book = new BookXMLParser(is).getBook();
        System.out.println("title:->" + book.getTitle());
        System.out.println("summary:->"+ book.getSummary());
        System.out.println("price:-->" + book.getPrice());
        System.out.println("author:-->" + book.getAuthor());
        System.out.println("ImagePath:-->" + book.getImagePath());
        /*int size = is.available();
        byte[] jsonBytes = new byte[size];
        is.read(jsonBytes);
        String message = new String(jsonBytes, "UTF-8");
        System.out.println(message);*/

    }
    public static TudouBookInfo getTuDouBookInfo(String url) throws ClientProtocolException, IOException{
        URL urlGet = new URL(url);
        HttpURLConnection http = (HttpURLConnection) urlGet
                .openConnection();
        http.setRequestMethod("GET"); // 必须是get方式请求
        http.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        http.setDoOutput(true);
        http.setDoInput(true);
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
        System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
        http.connect();
        InputStream is = http.getInputStream();
        System.out.println("查询出来的book================================="+is);
        TudouBookInfo book = new BookXMLParser(is).getBook();
        System.out.println("查询出来的book================================="+book);
        return book;
    }
    public static void main(String[] args) throws ClientProtocolException, IOException {
      // new RetrieveDocumentByURL("http://api.douban.com/book/subject/isbn/6947751402018");
        new RetrieveDocumentByURL("https://api.douban.com/v2/book/isbn/:6947751402018");
    }

}
