package com.xxhwap.controllers;

import com.xxhwap.services.ICoreService;
import com.xxhwap.utils.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 请求处理的核心类
 *
 */
@Controller
public class CoreServlet{
	private static final long serialVersionUID = 4440739483644821986L;
	/*
	 * 核心服务接口注入
	 */
	@Autowired
	public ICoreService coreService;
	/**
	 * 确认请求来自微信服务器
	 */
	@RequestMapping(value="/bookToken",method = RequestMethod.GET)
	@ResponseBody
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	/**
	 * 处理微信服务器发来的消息
	 */
	@RequestMapping(value="/bookToken",method = RequestMethod.POST)
	@ResponseBody
	public void doPost(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	   // String openid=coreService.getOpenId(request,response);
		//System.out.println(openid);
		// 调用核心业务类接收消息、处理消息
		//coreService.send_template_message(Config.APPID,Config.SECRET,"o-4SRs52-LjWlOtpxMs0maATbuSY", TemplateMessageContants.SH_TG_TEMPLATE_ID);
		String respMessage=coreService.processRequest(request, response);
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}
}
