/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2012-6-1
 */
package com.xxhwap.services.config;


import com.xxhwap.bo.ConfValue;

import java.util.Map;


/**
 * @title :项目配置部署
 * @description :项目配置部署interface类
 * @author: zhengYunFei
 * @data: 2013-7-11
 */
public interface IConfManage{


	public ConfValue findConfValueByMap(Map<String, Object> m);

}
