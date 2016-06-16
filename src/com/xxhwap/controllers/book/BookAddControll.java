package com.xxhwap.controllers.book;

import com.xxhwap.book.TudouBookInfo;
import com.xxhwap.contrants.MobilePageContants;
import com.xxhwap.services.IBookService;
import com.xxhwap.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 手机端主页调整控制
 * @author zhengyunfei
 * @date 2015-04-22
 *
 */

@Controller
public class BookAddControll {
	/**
	 * cancel sale
	 * @param request
	 * @param response
	 * @param model
     * @return
     */
	@RequestMapping(value = "/book/cancelSale.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> cancelSale(HttpServletRequest request,
								 HttpServletResponse response, ModelMap model,String id) {
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> result=new HashMap<String, Object>();
		try{
			boolean flg=bookService.delBookById(id);
			if(flg){
				result.put("result","cancelSaleSuccess");
			}else{
				result.put("result","error");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * dealFail
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/book/dealFail.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> dealFail(HttpServletRequest request,
										 HttpServletResponse response, ModelMap model,String id) {
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> result=new HashMap<String, Object>();
		try{
			//first update lastsaletime and status
			TudouBookInfo bookInfo=bookService.findById(id);
			if(!StringUtils.isEmpty(bookInfo)){
				String oid=bookInfo.getOid();
				//if oid is not null and status=0 then need set orign book num 同时 delete this book info
				if(!StringUtils.isEmpty(oid)){
					TudouBookInfo orignBook=bookService.findById(oid);
					if(!StringUtils.isEmpty(orignBook)){
						int ostatus=orignBook.getStatus();
						if(ostatus==0){
							//step 1 更新数据量
							int num=Integer.parseInt(bookInfo.getNumber());
							int onum=Integer.parseInt(orignBook.getNumber());
							int total=num+onum;
							orignBook.setNumber(total+"");
							bookService.updateBook(orignBook);
							//step 2 删除当前book info
							bookService.delBookById(id);
						}
					}
				}else {
					//else if oid is null then 只需要更新this book info status=0 and lastCancelSaleTime=now()
					 bookInfo.setStatus(MobilePageContants.STATUS_0);
					 String now=DateUtil.getBeforeNDaysTime(0);
					 bookInfo.setLastCancelSaleTime(now);
					bookService.updateBook(bookInfo);
				}

			}
			result.put("result","dealSuccess");

		}catch (Exception e){
			result.put("result","error");
			e.printStackTrace();
		}

		return result;
	}
	@Autowired
	protected IBookService bookService;

}