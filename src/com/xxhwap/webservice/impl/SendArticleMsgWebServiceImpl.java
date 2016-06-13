package com.xxhwap.webservice.impl;

import com.xxhwap.utils.Config;
import com.xxhwap.utils.GetAccessTokenUtil;
import com.xxhwap.utils.WeixinUtil;
import com.xxhwap.webservice.ISendArticleMsgWebService;
import net.sf.json.JSONObject;

import javax.jws.WebService;

/**
 * Created by Administrator on 2016/5/27.
 */
@WebService(endpointInterface="com.xxhwap.webservice.ISendArticleMsgWebService")
public class SendArticleMsgWebServiceImpl  implements ISendArticleMsgWebService {
    @Override
    public String send(String jsonStr) {
        JSONObject jsonObject=null;
        try {
            String url ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN"; //发送客服图文消息
            Config config=new Config();
            String appid=config.getString("appid");
            String appsecret=config.getString("appsecret");
            String token= GetAccessTokenUtil.getAccess_token2(appid,appsecret);
            url =url.replace("ACCESS_TOKEN", token);
             jsonObject = WeixinUtil.httpRequest(url, "POST", jsonStr);
            int result = 0;
            if (null != jsonObject) {
                if (0 != jsonObject.getInt("errcode")) {
                    result = jsonObject.getInt("errcode");
                }
            }
            System.out.println("result==========================================="+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  jsonObject.toString();
    }
}
