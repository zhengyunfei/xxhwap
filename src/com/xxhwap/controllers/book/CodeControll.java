package com.xxhwap.controllers.book;

import com.xxhwap.bo.CodeInfo;
import com.xxhwap.services.code.ICode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
public class CodeControll {
	/**
	 * cancel sale
	 * @param request
	 * @param response
	 * @param model
     * @return
     */
	@RequestMapping(value = "/code/getCodeInfo.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> cancelSale(HttpServletRequest request,
								 HttpServletResponse response, ModelMap model,String pCode,String codeSortId) {
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> result=new HashMap<String, Object>();
		List<CodeInfo> list=new ArrayList<CodeInfo>();
		try{
			if(!StringUtils.isEmpty(pCode)){
				map.put("pcode",pCode);
			}
			if(!StringUtils.isEmpty(codeSortId)){
				map.put("codeSortId",codeSortId);
			}
			list=code.findCodeInfoList(map);
			result.put("list",list);

		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Autowired
	protected ICode code;

}
