package com.xxhwap.services.impl;

import com.xxhwap.bo.*;
import com.xxhwap.contrants.CodeCommon;
import com.xxhwap.services.ICoreService;
import com.xxhwap.services.config.IConfManage;
import com.xxhwap.services.pic.IPictureService;
import com.xxhwap.utils.MessageUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 发送消息核心服务类
 */
@Component("coreService")
public class CoreServiceImpl implements ICoreService {
	@Resource(name="pictureService")
	private IPictureService pictureService;
	@Resource(name = "confManage")
	private IConfManage confManage;

	/*
	 * 发送消息核心服务接口注入
	 */
	/**
	 * 处理微信发来的请求
	 *
	 * @param request
	 * @return xml
	 */
	public  String processRequest(HttpServletRequest request,HttpServletResponse response) {
		String respMessage = "";
		try {
			// 默认返回的文本消息内容
			String respContent = "";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			String content=requestMap.get("Content");
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			//textMessage.setFuncFlag(0);
			// 创建图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			// newsMessage.setFuncFlag(0);
			List<Article> articleList = new ArrayList<Article>();
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				if(content.equals("zhengyunfei")){
					Article article = new Article();
					article.setTitle("文章标题");
					article.setDescription("");
					article.setPicUrl("http://www.pestreet.cn/c/freemarker/upload/img/20150618/20150618142923banner.jpg");
					article.setUrl("http://www.baidu.com");
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				if(content.equals("888888")){
					//respContent="";

				}

			}
			// 图片消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				//respContent += "您发送的是图片消息！";
				//respContent="";

			}
			// 地理位置消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				//respContent += "您发送的是地理位置消息！";
			}
			// 链接消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				// respContent += "您发送的是链接消息！";
			}
			// 音频消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				//respContent += "您发送的是音频消息！";
			}
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {

					respContent =getValue(CodeCommon.WelcomeValue);

				}
				// 取消订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					System.out.println("eventKey==========================="+eventKey);
					if(eventKey.equals("2")){
						respContent=getValue(CodeCommon.CLICK_KEY_ZHAOGONG);
					}
					if(eventKey.equals(CodeCommon.CLICK_KEY_1)){
						respContent=getValue(CodeCommon.CLICK_KEY_1);
						System.out.println("resContent==========================="+respContent);
					}
					if(eventKey.equals(CodeCommon.CLICK_KEY_2)){
						respContent=getValue(CodeCommon.CLICK_KEY_2);
					}
					if(eventKey.equals(CodeCommon.CLICK_KEY_3)){
						respContent=getValue(CodeCommon.CLICK_KEY_3);
					}
					if(eventKey.equals(CodeCommon.CLICK_KEY_4)){
						String picName=getValue(CodeCommon.CLICK_KEY_4);
						//发送图文消息1
						List<PictureEntity> list=pictureService.findPicturesByTypeName(picName);
						if(null!=list&&list.size()>0){
							PictureEntity entity=list.get(0);
							String title=entity.getName();
							String remark=entity.getRemark();
							String picUrl=entity.getAttachmentUrl();
							String url=entity.getUrl();
							Article article = new Article();
							article.setTitle(title);
							article.setDescription(remark);
							article.setPicUrl(picUrl);
							article.setUrl(url);
							articleList.add(article);
							// 设置图文消息个数
							newsMessage.setArticleCount(articleList.size());
							// 设置图文消息包含的图文集合
							newsMessage.setArticles(articleList);
							// 将图文消息对象转换成xml字符串
							respMessage = MessageUtil.newsMessageToXml(newsMessage);
						}


					}
					if(eventKey.equals(CodeCommon.CLICK_KEY_5)){
						String picName=getValue(CodeCommon.CLICK_KEY_5);
						//发送图文消息1
						List<PictureEntity> list=pictureService.findPicturesByTypeName(picName);
						if(null!=list&&list.size()>0){
							PictureEntity entity=list.get(0);
							String title=entity.getName();
							String remark=entity.getRemark();
							String picUrl=entity.getAttachmentUrl();
							String url=entity.getUrl();
							Article article = new Article();
							article.setTitle(title);
							article.setDescription(remark);
							article.setPicUrl(picUrl);
							article.setUrl(url);
							articleList.add(article);
							// 设置图文消息个数
							newsMessage.setArticleCount(articleList.size());
							// 设置图文消息包含的图文集合
							newsMessage.setArticles(articleList);
							// 将图文消息对象转换成xml字符串
							respMessage = MessageUtil.newsMessageToXml(newsMessage);
						}
					}
					if(eventKey.equals(CodeCommon.CLICK_KEY_6)){
						String picName=getValue(CodeCommon.CLICK_KEY_6);
						//发送图文消息1
						List<PictureEntity> list=pictureService.findPicturesByTypeName(picName);
						if(null!=list&&list.size()>0){
							PictureEntity entity=list.get(0);
							String title=entity.getName();
							String remark=entity.getRemark();
							String picUrl=entity.getAttachmentUrl();
							String url=entity.getUrl();
							Article article = new Article();
							article.setTitle(title);
							article.setDescription(remark);
							article.setPicUrl(picUrl);
							article.setUrl(url);
							articleList.add(article);
							// 设置图文消息个数
							newsMessage.setArticleCount(articleList.size());
							// 设置图文消息包含的图文集合
							newsMessage.setArticles(articleList);
							// 将图文消息对象转换成xml字符串
							respMessage = MessageUtil.newsMessageToXml(newsMessage);
						}
					}

				}
			}
			if(!StringUtils.isEmpty(respContent)) {
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
	public String getValue(String key){
		String result="";
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("confKey",key);
		ConfValue confValue=confManage.findConfValueByMap(m);
		result=confValue.getConfValue();
		return result;
	}

}
