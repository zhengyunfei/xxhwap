package com.xxhwap.book;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/5/31.
 */
public class XmlUtil {
    /**
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        InputStream is=downloadXML("https://api.douban.com/v2/book/isbn/:9787111128069");
        String outfile=convertStreamToString(is);
        System.out.println(outfile);

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //缓冲FileWriter
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }


    }

    public static InputStream downloadXML(final String urlStr)
    {
        InputStream inStream = null;
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        conn.setConnectTimeout(5 * 1000);
        try {
            conn.setRequestMethod("GET");
            conn.connect();
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }

        try {
            inStream= conn.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return inStream;
    }
    public static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));

        StringBuilder sb = new StringBuilder();



        String line = null;

        try {

            while ((line = reader.readLine()) != null) {

                sb.append(line + "/n");

            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                is.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }



        return sb.toString();

    }
}
