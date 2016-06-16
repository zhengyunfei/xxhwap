/**
 * Copyright (c) 2010 CEPRI,Inc.All rights reserved.
 * Created by 2012-6-1
 */
package com.xxhwap.services.config.impl;

import com.xxhwap.bo.ConfValue;
import com.xxhwap.dao.config.IConfigDao;
import com.xxhwap.services.config.IConfManage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @title :项目配置部署
 * @description :项目配置部署
 * @author: zhengYunFei
 * @data: 2013-7-11
 */
@Service("confManage")
public class ConfManageImpl implements IConfManage {

	@Resource(name = "configDao")
	private IConfigDao configDao;
	public ConfValue findConfValueByMap(Map<String, Object> m) {
		return configDao.findConfValueByMap(m);
	}

}
