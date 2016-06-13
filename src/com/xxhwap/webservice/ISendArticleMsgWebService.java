package com.xxhwap.webservice;

/**
 * Created by Administrator on 2016/5/27.
 */
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ISendArticleMsgWebService {
    public String send(@WebParam(name="jsondata") String jsondata);
}
