package com.xxhwap.common.freemarker.directives;

import com.xxhwap.bo.CodeInfo;
import com.xxhwap.services.code.ICode;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取文章数据freemarker驱动
 * @author zhengyunfei
 *
 */
public class FindCodeInfoListDirective implements TemplateDirectiveModel {

	/*
	 * 参数中supType与subType必须同时出线
	 */
	private static final String PARAM_PCODE = "pcode";
	private static final String PARAM_CODE_SORT_ID = "codeSortId";
	private static final String PARAM_CODE_VALUE = "codeValue";
	private static final String PARAM_COUNT = "count";
	private static final String PARAM_PAGE = "page";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] model,
						TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			Map<String,Object> queryMap=new HashMap<String, Object>();
			String pcode = DirectiveUtils.getString(PARAM_PCODE, params);
			String codeSortId = DirectiveUtils.getString(PARAM_CODE_SORT_ID, params);
			String codeValue= DirectiveUtils.getString(PARAM_CODE_VALUE, params);
			String count= DirectiveUtils.getString(PARAM_COUNT, params);
			String page= DirectiveUtils.getString(PARAM_PAGE, params);
			if(!StringUtils.isEmpty(pcode)){
				queryMap.put("pcode",pcode);
			}
			if(!StringUtils.isEmpty(codeSortId)){
				queryMap.put("codeSortId",codeSortId);
			}
			if(!StringUtils.isEmpty(codeValue)){
				queryMap.put("codeValue",codeValue);
			}
			if(!StringUtils.isEmpty(count)){
				int c=Integer.parseInt(count);
				queryMap.put("count",c);
				if(!StringUtils.isEmpty(page)){
					int p=Integer.parseInt(page);
					queryMap.put("start",(c*(p-1)+1));
				}else{
					queryMap.put("start",0);
				}
			}

			List<CodeInfo> list = new ArrayList<CodeInfo>();
			list = code.findCodeInfoList(queryMap);
			env.setVariable("codes", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		}catch (Exception e){
			e.printStackTrace();
		}
		body.render(env.getOut());
	}

	/*
	 * 文章服务接口注入
	 */
	@Autowired
	protected ICode code ;
}
