package com.xxhwap.dao.book.impl;

import com.xxhwap.book.TudouBookInfo;
import com.xxhwap.contrants.CodeCommon;
import com.xxhwap.dao.base.IbatisBaseDao;
import com.xxhwap.dao.book.IBookDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengyunfei
 *
 */
@Component("bookDao")
public class BookDaoImpl extends IbatisBaseDao implements IBookDao{
	private static final String COMMON = "com.xxhwap.book.";
	private static final String ADD = COMMON+"add";
	private static final String SAVE = COMMON+"save";
	private static final String UPDATE = COMMON+"update";
	private static final String UPDATE_ISVALID = COMMON+"updateIsValid";
	private static final String DELETE = COMMON+"delete";
	private static final String DELET_BY_ID = COMMON+"deleteById";
	private static final String FINDALLLIST = COMMON+"findAllList";
	private static final String FINDBYID = COMMON+"findById";
	private static final String GETCOUNT = COMMON+"getCount";
	private static final String LAST_SELECT_AREA = COMMON+"findLastHistoryArea";
	private static final String  LAST_SELECT_SCHOOL= COMMON+"findLastHistorySchool";
	@Override
	public long sendBook(TudouBookInfo bookInfo) {
		long id=0;
		try{
			id=(Long)this.insert(ADD,bookInfo);
		}catch (Exception e){
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public long saveBook(TudouBookInfo bookInfo) {
		long id=0;
		try{
			id=(Long)this.insert(SAVE,bookInfo);
		}catch (Exception e){
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public List<TudouBookInfo> findSendBookList(Map<String, Object> queryMap) {
		List<TudouBookInfo> list=new ArrayList<TudouBookInfo>();
		try {
			list= (List<TudouBookInfo>) this.queryAll(FINDALLLIST,queryMap);
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public TudouBookInfo findById(String id) {
		TudouBookInfo bookInfo=null;
		try {
			Map<String,Object> queryMap=new HashMap<String, Object>();
			queryMap.put("id",id);
			bookInfo= (TudouBookInfo) this.query(FINDBYID,queryMap);
		}catch (Exception e){
			e.printStackTrace();
		}
		return bookInfo;
	}

	@Override
	public boolean updateBook(TudouBookInfo bookInfo) {
		boolean flg=false;
		try{
			this.update(UPDATE,bookInfo);
			flg=true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return flg;
	}

	public int deleteByMap(Map<String, Object> queryMap) {
		int count=0;
		try{
			count=this.update(DELETE,queryMap);
		}catch (Exception e){
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getCount(Map<String, Object> map) {
		int count=0;
		try{
			count=(Integer) this.query(GETCOUNT,map);
		}catch (Exception e){
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean delBookById(String id) {
		boolean flg=false;
		try{
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("id",id);
			this.update(DELET_BY_ID,map);
			flg=true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return flg;
	}

	@Override
	public int findSendBookListCount(Map<String, Object> map) {
		int count=0;
		try{
			count=(Integer) this.query(GETCOUNT,map);
		}catch (Exception e){
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean updateBookForIsValid(TudouBookInfo updateBook) {
		boolean flg=false;
		try{
			this.update(UPDATE_ISVALID,updateBook);
			flg=true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return flg;

	}

	@Override
	public String findLastSelectAreaOrSchool(Map<String, Object> queryMap, String type) {
		String result="";
		try{
			if(CodeCommon.SCHOOL.equals(type)){
				result= (String) this.query(LAST_SELECT_SCHOOL,queryMap);
			}
			if(CodeCommon.AREA.equals(type)){
				result= (String) this.query(LAST_SELECT_AREA,queryMap);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
