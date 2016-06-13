package com.xxhwap.services;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZhengYunfei on 2015/7/16
 * 微信发送消息核心接口
 */
@Service
public interface ICoreService {
    /**
     * 微信发送普通消息接口
     * @param request
     * @param response
     * @return
     */
    public String processRequest(HttpServletRequest request, HttpServletResponse response) ;


}
