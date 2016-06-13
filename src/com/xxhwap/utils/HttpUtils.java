package com.xxhwap.utils;

/**
 * Created by Administrator on 2016/5/30.
 */
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http 工具类 ,依赖org.apache.http.* 和 org.apache.commons.httpclient.*
 *
 *
 * @date 2015 -09-14 上午9:56:10
 *
 * @author gaodebao
 *
 */
public class HttpUtils {

    private static final String DEFAULT_ENCODING = "utf-8";

    /**
     * GET方式请求
     *
     * @param uri
     * 服务器的uri要用物理IP或域名,不识别localhost或127.0.0.1形式!
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String get(String uri) throws ClientProtocolException,
            IOException {
        HttpGet httpGet = new HttpGet(uri);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGet);
        int statusCode;
        if ((statusCode = httpResponse.getStatusLine().getStatusCode()) == 200) {
            String result = EntityUtils.toString(httpResponse.getEntity());
            return result;
        }
        throw new IOException("status is " + statusCode);
    }

    public static String get(String uri, Map<String, String> paramMap)
            throws ClientProtocolException, IOException {

        StringBuilder sb = new StringBuilder(uri);
        if (paramMap != null) {
            boolean isBegin = true;
            for (String key : paramMap.keySet()) {
                if (isBegin) {
                    sb.append("?").append(key).append("=")
                            .append(paramMap.get(key));
                    isBegin = false;
                } else {
                    sb.append("&").append(key).append("=")
                            .append(paramMap.get(key));
                }

            }
        }
        HttpGet httpGet = new HttpGet(sb.toString());
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGet);
        int statusCode;
        if ((statusCode = httpResponse.getStatusLine().getStatusCode()) == 200) {
            String result = EntityUtils.toString(httpResponse.getEntity());
            return result;
        }
        throw new IOException("status is " + statusCode);
    }

    /**
     * GET方式请求https
     *
     * @param uri
     * 服务器的uri要用物理IP或域名,不识别localhost或127.0.0.1形式!
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String httpsGet(String uri, String keyFile, String keyPwd)
            throws Exception {
        HttpGet httpGet = new HttpGet(uri);
        HttpClient httpClient = newHttpsClient(keyFile, keyPwd);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        int statusCode;
        if ((statusCode = httpResponse.getStatusLine().getStatusCode()) == 200) {
            String result = EntityUtils.toString(httpResponse.getEntity());
            return result;
        }
        throw new IOException("status is " + statusCode);
    }

    /**
     * POST方式请求
     *
     * @param uri
     * 服务器的uri要用物理IP或域名,不识别localhost或127.0.0.1形式!
     * @param paramMap
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String post(String uri, Map<String, String> paramMap)
            throws ClientProtocolException, IOException {

        System.out.print(uri);

        HttpPost httpPost = new HttpPost(uri);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (paramMap != null) {
            for (String key : paramMap.keySet()) {
                params.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params,
                    DEFAULT_ENCODING));
        }
        HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
        int statusCode;
        if ((statusCode = httpResponse.getStatusLine().getStatusCode()) == 200) {
            return EntityUtils.toString(httpResponse.getEntity());
        }
        throw new IOException("status is " + statusCode);
    }

    /**
     * POST方式请求
     *
     * @param uri
     * 服务器的uri要用物理IP或域名,不识别localhost或127.0.0.1形式!
     * @param paramMap
     * @param headers
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String post(String uri, Map<String, String> paramMap,
                              Map<String, String> headers) throws ClientProtocolException,
            IOException {
        HttpPost httpPost = new HttpPost(uri);
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpPost.setHeader(key, headers.get(key));
            }
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (paramMap != null) {
            for (String key : paramMap.keySet()) {
                params.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            httpPost.setEntity(new ByteArrayEntity(paramMap.get("reqData").getBytes("UTF-8")));
//	httpPost.setEntity(new UrlEncodedFormEntity(params,
//	DEFAULT_ENCODING));
        }
        HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
        int statusCode;
        if ((statusCode = httpResponse.getStatusLine().getStatusCode()) == 200) {
            return EntityUtils.toString(httpResponse.getEntity());
        }
        throw new IOException("status is " + statusCode);
    }

    public static String post(String uri, String contentType, String content)
            throws Exception {
        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();

        PostMethod post = new PostMethod(uri);

        RequestEntity entity = new StringRequestEntity(content, contentType,DEFAULT_ENCODING);
        post.setRequestEntity(entity);

        int statusCode = client.executeMethod(post);
        if (statusCode == 200) {
            return post.getResponseBodyAsString();
        }
        throw new IOException("status is " + statusCode);
    }

// public static String post(String uri, String contentType, String content)
//	throws Exception {
//	org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
//
//	PostMethod post = new PostMethod(uri);
//
//	RequestEntity entity = new StringRequestEntity(content, contentType,DEFAULT_ENCODING);
//	post.setRequestEntity(entity);
//
//	int statusCode = client.executeMethod(post);
//	if (statusCode == 200) {
//	return post.getResponseBodyAsString();
//	}
//	throw new IOException("status is " + statusCode);
//	}

    /**
     * POST方式请求https
     *
     * @param uri
     * 服务器的uri要用物理IP或域名,不识别localhost或127.0.0.1形式!
     * @param paramMap
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String httpsPost(String uri, Map<String, String> paramMap,
                                   String keyFile, String keyPwd) throws ClientProtocolException,
            IOException, Exception {
        HttpPost httpPost = new HttpPost(uri);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (paramMap != null) {
            for (String key : paramMap.keySet()) {
                params.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params,
                    DEFAULT_ENCODING));
        }
        HttpResponse httpResponse = newHttpsClient(keyFile, keyPwd).execute(
                httpPost);
        int statusCode;
        if ((statusCode = httpResponse.getStatusLine().getStatusCode()) == 200) {
            return EntityUtils.toString(httpResponse.getEntity());
        }
        throw new IOException("status is " + statusCode);
    }

    /*
    * 新建httpsClient
    */
    private static HttpClient newHttpsClient(String keyFile, String keyPwd)
            throws Exception {
        KeyStore trustStore = KeyStore.getInstance("BKS");
        trustStore.load(new FileInputStream(new File(keyFile)),
                keyPwd.toCharArray());
        SSLSocketFactory socketFactory = new SSLSocketFactory(trustStore);
        Scheme sch = new Scheme("https", socketFactory, 8443);
        HttpClient client = new DefaultHttpClient();
        client.getConnectionManager().getSchemeRegistry().register(sch);
        return client;
    }
}
