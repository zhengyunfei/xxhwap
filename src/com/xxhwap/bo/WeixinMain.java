package com.xxhwap.bo;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 模拟登陆 获取数据 发送消息
 *
 * @author wang
 * @version V1.0
 */
public class WeixinMain {

	// 模拟登陆的url
	public static String LOGIN_URL = "http://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";
	// 模拟登陆后发送信息的url
	public static String SEND_MSG = "http://mp.weixin.qq.com/cgi-bin/singlesend?t=ajax-response&lang=zh_CN";
	public final static String HOST = "http://mp.weixin.qq.com";

	public final static String FANS_URL = "http://mp.weixin.qq.com/cgi-bin/contactmanagepage?t=wxm-friend&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0";
	public final static String LOGOUT_URL = "http://mp.weixin.qq.com/cgi-bin/logout?t=wxm-logout&lang=zh_CN";
	public final static String DOWNLOAD_URL = "http://mp.weixin.qq.com/cgi-bin/downloadfile?";
	public final static String VERIFY_CODE = "http://mp.weixin.qq.com/cgi-bin/verifycode?";
	public final static String POST_MSG = "https://mp.weixin.qq.com/cgi-bin/masssend?t=ajax-response";
	public final static String VIEW_HEAD_IMG = "http://mp.weixin.qq.com/cgi-bin/viewheadimg";
	public final static String GET_IMG_DATA = "http://mp.weixin.qq.com/cgi-bin/getimgdata";
	public final static String GET_REGIONS = "http://mp.weixin.qq.com/cgi-bin/getregions";
	public final static String GET_MESSAGE = "http://mp.weixin.qq.com/cgi-bin/getmessage";
	public final static String OPER_ADVANCED_FUNC = "http://mp.weixin.qq.com/cgi-bin/operadvancedfunc";
	public final static String MASSSEND_PAGE = "http://mp.weixin.qq.com/cgi-bin/masssendpage";
	public final static String FILE_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/filemanagepage";
	public final static String OPERATE_APPMSG = "https://mp.weixin.qq.com/cgi-bin/operate_appmsg";
	public final static String FMS_TRANSPORT = "http://mp.weixin.qq.com/cgi-bin/fmstransport";
	public final static String CONTACT_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/contactmanagepage";
	public final static String OPER_SELF_MENU = "http://mp.weixin.qq.com/cgi-bin/operselfmenu";
	public final static String REPLY_RULE_PAGE = "http://mp.weixin.qq.com/cgi-bin/replyrulepage";
	public final static String SINGLE_MSG_PAGE = "http://mp.weixin.qq.com/cgi-bin/singlemsgpage";
	public final static String USER_INFO_PAGE = "http://mp.weixin.qq.com/cgi-bin/userinfopage";
	public final static String DEV_APPLY = "http://mp.weixin.qq.com/cgi-bin/devapply";
	public final static String UPLOAD_MATERIAL = "https://mp.weixin.qq.com/cgi-bin/uploadmaterial?cgi=uploadmaterial&type=2&token=416919388&t=iframe-uploadfile&lang=zh_CN&formId=1";

	public final static String USER_AGENT_H = "User-Agent";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
	public final static String REFERER_H = "Referer";
	public final static String INDEX_URL = "http://mp.weixin.qq.com/cgi-bin/indexpage?t=wxm-index&lang=zh_CN";
	public final static String UTF_8 = "UTF-8";

	private final static HttpClient client = new HttpClient();

	public static Cookie[] cookies;
	public static String cookiestr;
	public static String TOKEN;
	public static int loginErrCode;
	public static String loginErrMsg;
	public static int msgSendCode;
	public static String msgSendMsg;
	public static HashMap<String, String> cook;
	public static int user_count;

	public static WeiXinUserBo getWechatUserInfo(String openId, String taken){
		WeiXinUserBo wxuser=new WeiXinUserBo();
		wxuser.setOpenid(openId);
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+taken+"&openid="+openId;
		net.sf.json.JSONObject result=null;
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(url);
		try {
			client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
			client.getHttpConnectionManager().getParams().setSoTimeout(60000);
			client.executeMethod(get);
			String returnJson=get.getResponseBodyAsString();
			int statusCode=client.executeMethod(get);
			returnJson = new String(returnJson.getBytes("ISO8859_1"),"UTF-8");
			result = net.sf.json.JSONObject.fromObject(returnJson);

			if(result.containsKey("nickname")){
				String nickname=result.get("nickname")+"";
				wxuser.setNickname(nickname);
			}
			String headimgurl="";
			if(result.containsKey("headimgurl")){
				headimgurl= result.get("headimgurl")+"";
				wxuser.setHeadimgurl(headimgurl);
			}

			String sex="";
			if(result.containsKey("sex")){
				sex=result.get("sex")+"";
				if("1".equals(sex)){
					wxuser.setSex("男");
				}else if("2".equals(sex)){
					wxuser.setSex("女");
				}else{
					wxuser.setSex(sex);
				}
			}
			String country="";
			if(result.containsKey("country")){
				country=result.get("country")+"";
				wxuser.setCountry(country);
			}
			String province="";
			if(result.containsKey("province")){
				province=result.get("province")+"";
				wxuser.setProvince(province);
			}
			String city="";
			if(result.containsKey("city")){
				city=result.get("city")+"";
				wxuser.setCity(city);
			}
			long subscribe_time = 0;
			if(result.containsKey("subscribe_time")){
				subscribe_time=result.getLong("subscribe_time");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String date = sdf.format(new Date(subscribe_time*1000));
				wxuser.setSubscribe_time(date);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return wxuser;
	}




}
