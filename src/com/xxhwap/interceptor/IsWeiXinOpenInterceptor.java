package com.xxhwap.interceptor;

import com.xxhwap.contrants.MobilePageContants;
import com.xxhwap.utils.Config;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 测试注入用户.
 */
public class IsWeiXinOpenInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ServletContext application =request.getSession().getServletContext();
        String openId=application.getAttribute(MobilePageContants.CURRENT_USER_OPENID)+"";
        String key=application.getAttribute(MobilePageContants.CURRENT_USER_KEY)+"";
        Config config = new Config();
        String appid = config.getString("appid");
        String appsecret = config.getString("appsecret");
        String domain = config.getString("domain");
        String  page="";
        if(StringUtils.isEmpty(openId)||"null".equals(openId)){
             page="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+domain+"/oauth/do.html&response_type=code&scope=snsapi_base&state="+key+"#wechat_redirect";

        }

    boolean flg = true;
    HttpSession session = request.getSession();
    if(openId == null || openId.equals("null"))
    {
            flg = true;
    }
    else
    {
        flg = false;
    }

    if(flg)
    {
       // response.sendRedirect(new StringBuffer(request.getContextPath()).append("book/error").toString());
        request.getRequestDispatcher(new StringBuffer("book/error").toString()).forward(request, response);

    }
    return true;
}

}
