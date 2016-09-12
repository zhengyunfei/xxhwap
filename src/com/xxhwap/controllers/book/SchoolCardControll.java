package com.xxhwap.controllers.book;

import com.xxhwap.book.SchoolCardBo;
import com.xxhwap.contrants.MobilePageContants;
import com.xxhwap.services.schoolcard.ISchoolCardService;
import com.xxhwap.utils.FmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
 * 手机端主页调整控制
 * @author zhengyunfei
 * @date 2015-04-22
 *
 */

@Controller
public class SchoolCardControll {
	@RequestMapping(value = "/card/index.html", method = RequestMethod.GET)
	public ModelAndView indexPage(HttpServletRequest request,
								  HttpServletResponse response, ModelMap model) {
		ModelAndView mv=new ModelAndView();
		FmUtils.FmData(request,model);
		mv.setViewName(MobilePageContants.SCHOOL_CARD_INDEX);
		//isWeiXinOpenLink(request, mv);
		return mv;
	}

	/**
	 * 查询校园卡
	 * @param request
	 * @param response
	 * @param model
     * @return
     */
	@RequestMapping(value = "/card/query.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> query(HttpServletRequest request,
									HttpServletResponse response, ModelMap model, SchoolCardBo bo) {
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> result=new HashMap<String, Object>();
		boolean flg=false;
		try{
				map.put("school",bo.getSchool());
				map.put("cardNo",bo.getCardNo());
				map.put("flag",bo.getFlag());
				int count=schoolCardService.getCount(map);
				if(count>0){
					List<SchoolCardBo> list=schoolCardService.findSendSchoolCardList(map);
					result.put("card",list.get(0));
				}
				result.put("count",count);
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 发布校园卡信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/card/send.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> send(HttpServletRequest request,
										 HttpServletResponse response, ModelMap model,SchoolCardBo bo) {
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> result=new HashMap<String, Object>();
		try{
			//如果数据库中存在此卡号，则不用再发布
			map.put("school",bo.getSchool());
			map.put("cardNo",bo.getCardNo());
			map.put("flag",bo.getFlag());
			int count=schoolCardService.getCount(map);
			if(count==0){
				//保存发布记录
				schoolCardService.saveSchoolCard(bo);
				result.put("success",true);
			}else{
				result.put("success",true);
			}
		}catch (Exception e){
			result.put("success","error");
			e.printStackTrace();
		}

		return result;
	}
	@Autowired
	protected ISchoolCardService schoolCardService;

}
