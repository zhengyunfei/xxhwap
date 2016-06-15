package com.xxhwap.utils.weixin;

/**
 * Created by Administrator on 2015/7/22.
 */
public class UrlContants {
    public final static String get_access_token_url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
            "appid=APPID" +
            "&secret=SECRET&" +
            "code=CODE&grant_type=authorization_code";
    public final static String get_userinfo_url="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**微信公众号自定义菜单key**/
    public final static String MENU_KEY_1="1";//buy book
    public final static String MENU_KEY_2="2";//sell book
    public final static String MENU_KEY_3="3";//my buy book
    public final static String MENU_KEY_5="5";//my sell book

}
