package com.xxhwap.controllers.book;

import com.xxhwap.book.TudouBookInfo;
import com.xxhwap.contrants.MobilePageContants;
import com.xxhwap.services.IBookService;
import com.xxhwap.token.Token;
import com.xxhwap.utils.Config;
import com.xxhwap.utils.FmUtils;
import com.xxhwap.utils.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * send book opertion
 * @author zhengyunfei
 * @date 2015-04-22
 *
 */

@Controller
public class WebUIPageControll {
	/**
	 * send book page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/book/sell.html", method = RequestMethod.GET)
	public ModelAndView indexPage(HttpServletRequest request,
								  HttpServletResponse response, ModelMap model) {
		ModelAndView mv=new ModelAndView();
		FmUtils.FmData(request,model);
		sweepParam(request, mv);//获取扫一扫参数
		mv.setViewName(MobilePageContants.SELL_BOOK_PAGE);
		BookPageControll.isWeiXinOpenLink(request,mv);
		return mv;
	}
	private void sweepParam(HttpServletRequest request, ModelAndView mv) {
		String url=request.getRequestURL().toString();
		Map<String, String> res= Sign.getConfigMessageForWater(url);
		Config config=new Config();
		mv.addObject("appid",config.getString("appid"));
		mv.addObject("timestamp",res.get("timestamp"));
		mv.addObject("nonceStr",res.get("nonceStr"));
		mv.addObject("signature",res.get("signature"));
	}
	/**
	 * send book page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/webui/demo.html", method = RequestMethod.GET)
	public ModelAndView demo(HttpServletRequest request,
								  HttpServletResponse response, ModelMap model) {
		ModelAndView mv=new ModelAndView();
		FmUtils.FmData(request,model);
		mv.setViewName(MobilePageContants.WEBUI_DEMO_PAGE);
		return mv;
	}
	/**
	 * send book save
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/webui/sendbook.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> sendbook(HttpServletRequest request,
									   HttpServletResponse response, ModelMap model, TudouBookInfo bookInfo) {
		Map<String,Object> resultMap=new HashMap<String, Object>();
		//从缓存中获取openid
		//ServletContext application =request.getSession().getServletContext();
		//保存用户的openid到全局缓存中
		String openId=request.getSession().getAttribute(MobilePageContants.CURRENT_USER_OPENID)+"";
		if(!StringUtils.isEmpty(openId)){
			bookInfo.setOpenId(openId);
		}
		String page="";
		System.out.println("发布者openid＝＝＝＝＝＝＝＝"+openId);
		long id=bookService.sendBook(bookInfo);
		if(id>0){
			resultMap.put("success",true);
			resultMap.put("id",id);//保存成功返回主键
		}else{
			resultMap.put("success",false);
		}
		return resultMap;
	}
	/**
	 * send book page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/webui/sendBookList.html", method = RequestMethod.GET)
	public ModelAndView sendBookList(HttpServletRequest request,
								  HttpServletResponse response, ModelMap model) {
		ModelAndView mv=new ModelAndView();
		FmUtils.FmData(request,model);
		mv.setViewName(MobilePageContants.MY_SEND_LIST_BOOK);
		return mv;
	}

	/**
	 * query
	 * @param request
	 * @param response
	 * @param model
     * @return
     */
	@RequestMapping(value = "/webui/querybook.html", method = RequestMethod.GET)
	public ModelAndView querybook(HttpServletRequest request,
									 HttpServletResponse response, ModelMap model,TudouBookInfo bookInfo) {
		ModelAndView mv=new ModelAndView();
		FmUtils.FmData(request,model);
		Map<String,Object> queryMap=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(bookInfo)){
			String title=bookInfo.getTitle();
			String author=bookInfo.getAuthor();
			String publisher=bookInfo.getPublisher();
			String binding=bookInfo.getBinding();
			String school=bookInfo.getSchool();
			String biji=bookInfo.getBiji();
			/*String number=bookInfo.getNumber()+"";*/
			queryMap.put("status",MobilePageContants.STATUS_0);//default query not sale
			queryMap.put("isValid",MobilePageContants.STATUS_1);//default query not sale
			queryMap.put("number",MobilePageContants.STATUS_1);//default kucun dayu dengyu1
			if(!StringUtils.isEmpty(title)){
				queryMap.put("title",title);
			}
			if(!StringUtils.isEmpty(author)){
				queryMap.put("author",author);
			}
			if(!StringUtils.isEmpty(publisher)){
				queryMap.put("publisher",publisher);
			}
			if(!StringUtils.isEmpty(binding)){
				queryMap.put("binding",binding);
			}
			if(!StringUtils.isEmpty(school)){
				queryMap.put("school",school);
			}
			if(!StringUtils.isEmpty(biji)){
				queryMap.put("biji",biji);
			}
			/*if(!StringUtils.isEmpty(number)){
				queryMap.put("number",number);
			}*/
		}
		List<TudouBookInfo> list=bookService.findSendBookList(queryMap);
		if(!StringUtils.isEmpty(list)&&list.size()>0){
			mv.addObject("books",list);
		}
		mv.setViewName(MobilePageContants.MY_QUERY_LIST_RESULT);
		return mv;
	}
	/**
	 * query
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/book/detail{id}.html", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request,
								  HttpServletResponse response, ModelMap model,@PathVariable String  id) {
		ModelAndView mv=new ModelAndView();
		FmUtils.FmData(request,model);
		Map<String,Object> queryMap=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(id)) {
			TudouBookInfo bookInfo=bookService.findById(id);
			if(!StringUtils.isEmpty(bookInfo)){
				mv.addObject("book",bookInfo);
			}
		}
		mv.setViewName(MobilePageContants.MY_DETAIL_PAGE);
		BookPageControll.isWeiXinOpenLink(request,mv);
		return mv;
	}
	/**
	 * pay page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/webui/payPage.html", method = RequestMethod.GET)
	@Token(save=true)
	public ModelAndView gonext(HttpServletRequest request,
							   HttpServletResponse response, ModelMap model, String  id,int number) {
		ModelAndView mv=new ModelAndView();
		FmUtils.FmData(request,model);
		if(!StringUtils.isEmpty(id)) {
			TudouBookInfo bookInfo=bookService.findById(id);
			if(!StringUtils.isEmpty(bookInfo)){
				bookInfo.setNumber(number);
				float price=Float.parseFloat(bookInfo.getPrice());
				float totalPrice=number *price;
				bookInfo.setTotalPrice(totalPrice+"");
				mv.addObject("book",bookInfo);
			}
		}
		mv.setViewName(MobilePageContants.MY_PAY_PAGE);
		mv.addObject("token",id);
		return mv;
	}

	/**
	 * pay save page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/webui/paysave.html", method = RequestMethod.POST)
	@Token(remove=true)
	public ModelAndView paysave(HttpServletRequest request,
								   HttpServletResponse response, ModelMap model, String  id,String number) {
		ModelAndView mv=new ModelAndView();
		if(!StringUtils.isEmpty(id)) {
			TudouBookInfo bookInfo=bookService.findById(id);
			if(!StringUtils.isEmpty(bookInfo)){
				int onumber=bookInfo.getNumber();
				int num=Integer.parseInt(number);
				//pan duan yong hu buy num is not larger ku cun
				if(num<=onumber){
					//step 1 add book to databa
					TudouBookInfo newBookInfo=bookInfo;
					newBookInfo.setNumber(num);
					newBookInfo.setStatus(MobilePageContants.STATUS_1);//saled
					newBookInfo.setOid(id);
					//从缓存中获取openid
					//ServletContext application =request.getSession().getServletContext();
					//保存用户的openid到全局缓存中
					String openId=request.getSession().getAttribute(MobilePageContants.CURRENT_USER_OPENID)+"";
					System.out.println("购买者openid＝＝＝＝＝＝＝＝＝＝＝＝＝＝"+openId);
					if(!StringUtils.isEmpty(openId)){
						bookInfo.setOpenId(openId);
					}
					newBookInfo.setOpenId(openId);
					long pid=bookService.saveBook(newBookInfo);
					System.out.println("保存成功==============================="+pid);
					//step 2 update ori book num=onumber-num
					bookInfo.setId(Long.parseLong(id));
					bookInfo.setNumber((onumber-num));
					bookInfo.setStatus(MobilePageContants.STATUS_0);
					bookService.updateBook(bookInfo);
					id=pid+"";
				}else{//if num=onumber
					//update book pay_status
					bookInfo.setStatus(MobilePageContants.STATUS_1);
					bookService.updateBook(bookInfo);
				}

			}
		}
		mv.setViewName("redirect:/webui/ps"+id+".html");
		BookPageControll.isWeiXinOpenLink(request,mv);
		return mv;
	}
	/**
	 * pay success page
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/webui/ps{id}.html", method = RequestMethod.GET)
	public ModelAndView paysuccess(HttpServletRequest request,
							   HttpServletResponse response, ModelMap model, @PathVariable String  id) {
		ModelAndView mv=new ModelAndView();
		FmUtils.FmData(request,model);
		if(!StringUtils.isEmpty(id)) {
			TudouBookInfo bookInfo=bookService.findById(id);
			if(!StringUtils.isEmpty(bookInfo)){
				int num=bookInfo.getNumber();
				float price=Float.parseFloat(bookInfo.getPrice());
				float totalPrice=num*price;
				bookInfo.setTotalPrice(totalPrice+"");
				mv.addObject("book",bookInfo);
			}
		}
		mv.setViewName(MobilePageContants.MY_PAY_SUCCESS_PAGE);
		BookPageControll.isWeiXinOpenLink(request,mv);
		return mv;
	}



	@Autowired
	protected IBookService bookService;

}
