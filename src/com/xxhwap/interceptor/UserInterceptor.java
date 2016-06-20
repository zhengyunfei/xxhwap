package com.xxhwap.interceptor;

import com.xxhwap.contrants.MobilePageContants;
import com.xxhwap.utils.Config;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/6/20.
 */
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object obj, Exception err)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object obj, ModelAndView mav) throws Exception {
        Config config = new Config();
        String appid = config.getString("appid");
        String appsecret = config.getString("appsecret");
        String domain = config.getString("domain");
        ServletContext application =request.getSession().getServletContext();
        String openId=application.getAttribute(MobilePageContants.CURRENT_USER_OPENID)+"";
        String key=application.getAttribute(MobilePageContants.CURRENT_USER_KEY)+"";
        String  page="";
            page="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+domain+"/oauth/do.html&response_type=code&scope=snsapi_base&state="+key+"#wechat_redirect";
        request.getSession().setAttribute("errorPage",page);
        response.sendRedirect(page);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object obj) throws Exception {
        String url = "";
        url = request.getScheme() +"://" + request.getServerName()
                + ":" +request.getServerPort()
                + request.getServletPath();

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
        if(openId!=null&&!"null".equals(openId)){
            return true;
        }else{
           // request.getRequestDispatcher("/error.html").forward(request, response);
           // response.sendRedirect("http://localhost:8080/book/sell.html");
            return true;
        }
    }
}
