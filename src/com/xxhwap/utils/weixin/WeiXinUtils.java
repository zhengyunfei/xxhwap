package com.xxhwap.utils.weixin;

import com.xxhwap.utils.Config;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import static com.xxhwap.utils.WeixinUtil.httpRequest;

/**
 * Created by Administrator on 2016/5/31.
 */
public class WeiXinUtils {
    public final static String weixin_jssdk_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
// 微信JSSDK的AccessToken请求URL地址
public final static String weixin_jssdk_acceToken_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * * 获取微信JSSDK的access_token
     *  * @author Benson
     *  */
    public static String getJSSDKAccessToken() {
        String returnString="";
        Config config=new Config();
        String appid=config.getString("appid");
        String appsecret=config.getString("appsecret");
        String requestUrl = weixin_jssdk_acceToken_url.replace("APPID",appid).replace("APPSECRET",appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        //Http GET请求 // 如果请求成功
        if (null != jsonObject) {
            try {
                returnString=jsonObject.getString("access_token");
            } catch (JSONException e) {
                returnString = null;
            }
        }
        return returnString;
    }
    /**
     * 获取微信JSSDK的ticket
     * @author Benson
     */
    public static String getJSSDKTicket(String access_token) {
        String returnString="";
        String requestUrl = weixin_jssdk_ticket_url.replace("ACCESS_TOKEN", access_token);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                returnString=jsonObject.getString("ticket");
            } catch (JSONException e) {
                returnString = null;
            }
        }
        return returnString;
    }
}
