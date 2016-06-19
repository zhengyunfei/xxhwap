/**
 * @(#)ICodeManage.java	07/03/2013
 *
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-03
 */
package com.xxhwap.dao.code;


import com.xxhwap.bo.CodeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @title: 标准代码管理维接口定义
 * @description: 针对标准代码分类管理、标准代码项的一个维护管理接口的定义
 * @author： zhengYunFei
 * @date： 2013-07-03 10:10
 */
@Repository
public interface ICodeDao {
	public List<CodeInfo> findCodeInfoList(Map<String,Object> queryMap);

}
