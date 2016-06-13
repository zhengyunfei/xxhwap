package com.xxhwap.controllers;
import com.xxhwap.bo.WeiXinUserBo;
import com.xxhwap.bo.WeixinMain;
import com.xxhwap.utils.Config;
import com.xxhwap.utils.HttpUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by Administrator on 2015/7/10.
 */
@Controller
public class Oauth2Servlet {
    private static final long serialVersionUID = -644518508267758016L;

    @RequestMapping(value = "/oauth/do.html", method = RequestMethod.GET)
    public ModelAndView oauth(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=APPID" +
                "&secret=SECRET&" +
                "code=CODE&grant_type=authorization_code";
        String get_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        ModelAndView mv = new ModelAndView();
        try {
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            // xml请求解析
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String code = request.getParameter("code");
            //判断页面跳转
            String key = request.getParameter("state");
            Config config = new Config();
            String appid = config.getString("appid");
            String appsecret = config.getString("appsecret");
            get_access_token_url = get_access_token_url.replace("APPID", appid);
            get_access_token_url = get_access_token_url.replace("SECRET", appsecret);
            get_access_token_url = get_access_token_url.replace("CODE", code);
            String json = HttpUtil.getUrl(get_access_token_url);
            String openid = "";
            //获取当前登录的用户id

            JSONObject jsonObject = JSONObject.fromObject(json);
            if (jsonObject.has("openid")) {
                openid = jsonObject.getString("openid");
            }
            mv.addObject("openId", openid);
            String access_token = jsonObject.getString("access_token");
            WeiXinUserBo bo = WeixinMain.getWechatUserInfo(appid, access_token);
            mv.addObject("bo", bo);
            if (!StringUtils.isEmpty(bo)) {
                System.out.println("昵称=====================================" + bo.getNickname());
            }
            response.setContentType("text/html; charset=utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("userInfo");
        return mv;
    }
}
