package com.xxhwap.common.freemarker.directives;

import com.xxhwap.book.TudouBookInfo;
import com.xxhwap.contrants.MobilePageContants;
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
	private static final String PARAM_STATUS = "status";
	private static final String PARAM_PAGE = "page";
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] model,
						TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			Map<String,Object> queryMap=new HashMap<String, Object>();
			String openId = DirectiveUtils.getString(PARAM_VALUE, params);
			String flag = DirectiveUtils.getString(PARAM_FLAG, params);
			String count= DirectiveUtils.getString(PARAM_COUNT, params);
			String page= DirectiveUtils.getString(PARAM_PAGE, params);
			String status= DirectiveUtils.getString(PARAM_STATUS, params);
			if(!StringUtils.isEmpty(openId)){
				queryMap.put("openId",openId);
			}
			if(!StringUtils.isEmpty(status)){
				queryMap.put("status",status);
			}
			if(!StringUtils.isEmpty(flag)){
				queryMap.put("role",flag);
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
			List<TudouBookInfo> list = new ArrayList<TudouBookInfo>();
			List<TudouBookInfo> queryList = new ArrayList<TudouBookInfo>();
			list = bookService.findSendBookList(queryMap);
			//有可能我卖的书，被分开买了，所以要根据oid查询出来
			List<TudouBookInfo> childList=new ArrayList<TudouBookInfo>();
			int size=list.size();
			for(int i=0;i<size;i++){
				queryList.add(list.get(i));
				String id=list.get(i).getId()+"";//此id作为oid去查询
				Map<String,Object> m=new HashMap<String, Object>();
				m.put("oid",id);
				childList=bookService.findSendBookList(m);
				if(!StringUtils.isEmpty(childList)&&childList.size()>0){
					//那么需要将这一部分也加到我卖的书里面
					int childSize=childList.size();
					for(int j=0;j<childSize;j++){
						queryList.add(childList.get(j));
					}
				}

			}
			List<TudouBookInfo> result=new ArrayList<TudouBookInfo>();
			String now= DateUtil.getBeforeNDaysTime(2);
			for(int m=0;m<queryList.size();m++){
				int isCancel=1;
				String lastCancelDealTime=queryList.get(m).getLastCancelSaleTime();
				if(!StringUtils.isEmpty(lastCancelDealTime)){
					int c=DateUtil.compare_date(now,lastCancelDealTime);
					if(c<=0){//不可交易
						isCancel=0;
					}
				}
				queryList.get(m).setIsCancel(isCancel);
				//去掉无效的标示
				int isValid=queryList.get(m).getIsValid();
				int kucun=queryList.get(m).getNumber();
				if(MobilePageContants.STATUS_1==isValid&&kucun>0){
					result.add(queryList.get(m));
				}
			}
			env.setVariable("books", ObjectWrapper.DEFAULT_WRAPPER.wrap(result));
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
