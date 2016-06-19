/**
 * @(#)ICodeManage.java	07/03/2013
 *
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-03
 */
package com.xxhwap.services.code.impl;

import com.xxhwap.bo.CodeInfo;
import com.xxhwap.dao.code.ICodeDao;
import com.xxhwap.services.code.ICode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @title: 标准代码维护接口实现类
 * @description: 针对标准代码分类管理、标准代码项的一个维护管理接口的实现
 * @author： zhengYunFei
 * @date： 2013-07-03 10:10
 */
@Service("code")
public class CodeImpl implements ICode {

	@Resource(name = "codeDao")
	private ICodeDao codeDao;


	/**
	 * @return list 代码分类列表
	 * @title: 获取代码分类信息树
	 * @description: 获取代码分类信息树
	 * @author: zhengYunFei
	 * @date: 2013-07-03 10:20
	 */
	@Override
	public List<CodeInfo> findCodeInfoList(Map<String,Object> queryMap) {
		return codeDao.findCodeInfoList(queryMap);
	}
}
