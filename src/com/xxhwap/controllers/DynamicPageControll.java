package com.xxhwap.controllers;

import com.xxhwap.bo.WeiXinUserBo;
import com.xxhwap.services.ICoreService;
import com.xxhwap.utils.Config;
import com.xxhwap.utils.GetAccessTokenUtil;
import com.xxhwap.utils.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 * 手机端主页调整控制
 * @author zhengyunfei
 * @date 2015-04-22
 *
 */

@Controller
public class DynamicPageControll {


	/**
	 * 首页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView indexPage(HttpServletRequest request,
								  HttpServletResponse response, ModelMap model) {
		ModelAndView mv=new ModelAndView();
		try {
			mv.setViewName("userInfo");
			WeiXinUserBo bo=new WeiXinUserBo();
			bo.setCity("中国");
			bo.setCountry("");
			bo.setNickname("linghuchong");
			bo.setOpenid("123456");
			mv.addObject("bo",bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	/**
	 * 发送图文
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/send.html", method = RequestMethod.GET)
	public ModelAndView send(HttpServletRequest request,
								  HttpServletResponse response, ModelMap model) {
		ModelAndView mv=new ModelAndView();
		try {
			mv.setViewName("sendArticle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	/**
	 * 发送图文
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sendAjax.html", method = RequestMethod.POST)
	@ResponseBody
	public void sendAjax(HttpServletRequest request,
							 HttpServletResponse response, ModelMap model) {
		ModelAndView mv=new ModelAndView();
		try {
			request.setCharacterEncoding("UTF-8"); //声明request的字符集
			String jsonStr = request.getParameter("json");
			String url ="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN"; //发送客服图文消息
			Config config=new Config();
			String appid=config.getString("appid");
			String appsecret=config.getString("appsecret");
			String token= GetAccessTokenUtil.getAccess_token2(appid,appsecret);
			url =url.replace("ACCESS_TOKEN", token);
			JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonStr);
			int result = 0;
			if (null != jsonObject) {
				if (0 != jsonObject.getInt("errcode")) {
					result = jsonObject.getInt("errcode");
				}
			}
			// 响应消息
			PrintWriter out = response.getWriter();
			out.print(jsonObject.toString());
			out.close();
			// 图片消息
   System.out.println("result==========================================="+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	protected ICoreService coreService;

}
