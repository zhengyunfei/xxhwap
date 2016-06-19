/**
 * @(#)ICodeManage.java	07/03/2013
 *
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-03
 */
package com.xxhwap.services.code;


import com.xxhwap.bo.CodeInfo;
import com.xxhwap.bo.CodeSort;

import java.util.List;
import java.util.Map;

/**
 * @title: 标准代码管理维接口定义
 * @description: 针对标准代码分类管理、标准代码项的一个维护管理接口的定义
 * @author： zhengYunFei
 * @date： 2013-07-03 10:10
 */
public interface ICode {
	/**
     * @title: 获取代码分类信息树
     * @description: 获取代码分类信息树
     * @param bo {codeSortType:(代码分类信息，非必须参数),codeSortName:(代码分类名称，非必须参数)}
     * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return list 代码分类列表
     */
	public List<CodeInfo> findCodeInfoList(Map<String,Object> queryMap);


}
