package com.xxhwap.dao.code.impl;

import com.xxhwap.bo.CodeInfo;
import com.xxhwap.dao.base.IbatisBaseDao;
import com.xxhwap.dao.code.ICodeDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhengyunfei
 *
 */
@Component("codeDao")
public class CodeDaoImpl extends IbatisBaseDao implements ICodeDao{
	private static final String COMMON = "com.xxhwap.code.";
	private static final String FINDALLLIST = COMMON+"selectPCodesInfo";

	@Override
	public List<CodeInfo> findCodeInfoList(Map<String, Object> queryMap) {
		List<CodeInfo> list=new ArrayList<CodeInfo>();
		try {
			list= (List<CodeInfo>) this.queryAll(FINDALLLIST,queryMap);
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}


}
