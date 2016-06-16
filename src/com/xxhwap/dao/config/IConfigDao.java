package com.xxhwap.dao.config;

import com.xxhwap.bo.ConfValue;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * cfjCollection 实体类
 * Thu Apr 30 11:19:31 GMT+08:00 2015 zhengyunfei
 */

@Service
public interface IConfigDao {
	public ConfValue findConfValueByMap(Map<String, Object> m) ;
	public String getValue(String key) ;



}

