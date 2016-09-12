package com.xxhwap.dao.schoolcard.impl;

import com.xxhwap.book.SchoolCardBo;
import com.xxhwap.dao.base.IbatisBaseDao;
import com.xxhwap.dao.schoolcard.ISchoolCardDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengyunfei
 *
 */
@Component("schoolCardDao")
public class SchoolCardDaoImpl extends IbatisBaseDao implements ISchoolCardDao {
	private static final String COMMON = "com.xxhwap.SchoolCard.";
	private static final String ADD = COMMON+"add";
	private static final String SAVE = COMMON+"save";
	private static final String UPDATE = COMMON+"update";
	private static final String UPDATE_ISVALID = COMMON+"updateIsValid";
	private static final String DELETE = COMMON+"delete";
	private static final String DELET_BY_ID = COMMON+"deleteById";
	private static final String FINDALLLIST = COMMON+"findAllList";
	private static final String FINDBYID = COMMON+"findById";
	private static final String GETCOUNT = COMMON+"getCount";
	@Override
	public long sendSchoolCard(SchoolCardBo SchoolCardInfo) {
		long id=0;
		try{
			id=(Long)this.insert(ADD,SchoolCardInfo);
		}catch (Exception e){
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public long saveSchoolCard(SchoolCardBo SchoolCardInfo) {
		long id=0;
		try{
			id=(Long)this.insert(ADD,SchoolCardInfo);
		}catch (Exception e){
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public List<SchoolCardBo> findSendSchoolCardList(Map<String, Object> queryMap) {
		List<SchoolCardBo> list=new ArrayList<SchoolCardBo>();
		try {
			list= (List<SchoolCardBo>) this.queryAll(FINDALLLIST,queryMap);
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public SchoolCardBo findById(String id) {
		SchoolCardBo SchoolCardInfo=null;
		try {
			Map<String,Object> queryMap=new HashMap<String, Object>();
			queryMap.put("id",id);
			SchoolCardInfo= (SchoolCardBo) this.query(FINDBYID,queryMap);
		}catch (Exception e){
			e.printStackTrace();
		}
		return SchoolCardInfo;
	}

	@Override
	public boolean updateSchoolCard(SchoolCardBo SchoolCardInfo) {
		boolean flg=false;
		try{
			this.update(UPDATE,SchoolCardInfo);
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
	public boolean delSchoolCardById(String id) {
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
	public int findSendSchoolCardListCount(Map<String, Object> map) {
		int count=0;
		try{
			count=(Integer) this.query(GETCOUNT,map);
		}catch (Exception e){
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean updateSchoolCardForIsValid(SchoolCardBo updateSchoolCard) {
		boolean flg=false;
		try{
			this.update(UPDATE_ISVALID,updateSchoolCard);
			flg=true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return flg;

	}
}
