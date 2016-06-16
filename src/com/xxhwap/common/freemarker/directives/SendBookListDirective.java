package com.xxhwap.common.freemarker.directives;

import com.xxhwap.book.TudouBookInfo;
import com.xxhwap.services.IBookService;
import com.xxhwap.utils.DateUtil;
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
public class SendBookListDirective implements TemplateDirectiveModel {

	/*
	 * 参数中supType与subType必须同时出线
	 */
	private static final String PARAM_VALUE = "openId";
	private static final String PARAM_COUNT = "count";
	private static final String PARAM_FLAG = "flag";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] model,
						TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			Map<String,Object> queryMap=new HashMap<String, Object>();
			String openId = DirectiveUtils.getString(PARAM_VALUE, params);
			String flag = DirectiveUtils.getString(PARAM_FLAG, params);
			if(!StringUtils.isEmpty(openId)){
				queryMap.put("openId",openId);
			}
			if(!StringUtils.isEmpty(flag)){
				queryMap.put("role",flag);
			}
			if(params.containsKey("count")){
				int count = DirectiveUtils.getInt(PARAM_COUNT, params);
				if(!StringUtils.isEmpty(count)){
					queryMap.put("count",count);
				}
			}
			List<TudouBookInfo> list = new ArrayList<TudouBookInfo>();
			list = bookService.findSendBookList(queryMap);
			int size=list.size();
			String now= DateUtil.getBeforeNDaysTime(2);
			for(int i=0;i<size;i++){
				int isCancel=1;
				String lastCancelDealTime=list.get(i).getLastCancelSaleTime();
				if(!StringUtils.isEmpty(lastCancelDealTime)){
					int c=DateUtil.compare_date(now,lastCancelDealTime);
					if(c<=0){//不可交易
						isCancel=0;
					}
				}
				list.get(i).setIsCancel(isCancel);
			}
			env.setVariable("books", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
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
