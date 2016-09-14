package com.xxhwap.common.freemarker.directives;

import com.xxhwap.services.IBookService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 记录用户选择的地区和学校历史
 * type:school表示最近选择的学校记录，type:area表示最近选择的地区
 * @author zhengyunfei
 *
 */
public class FindSelectHistoryByOpenIdDirective implements TemplateDirectiveModel {

	/*
	 * 参数中supType与subType必须同时出线
	 */
	private static final String PARAM_VALUE = "openId";
	private static final String PARAM_TYPE = "type";
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] model,
						TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			Map<String,Object> queryMap=new HashMap<String, Object>();
			String openId = DirectiveUtils.getString(PARAM_VALUE, params);
			String type = DirectiveUtils.getString(PARAM_TYPE, params);
			if(!StringUtils.isEmpty(openId)){
				queryMap.put("openId",openId);
			}
			String codeValue=bookService.findLastSelectAreaOrSchool(queryMap,type);
			env.setVariable("codeValue", ObjectWrapper.DEFAULT_WRAPPER.wrap(codeValue));
		}catch (Exception e){
			e.printStackTrace();
		}
		body.render(env.getOut());
	}

	/*
	 * 文章服务接口注入
	 */
	@Autowired
	protected IBookService bookService ;
}
