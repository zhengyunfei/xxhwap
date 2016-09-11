package com.xxhwap.services.schoolcard.impl;

import com.xxhwap.book.SchoolCardBo;
import com.xxhwap.dao.schoolcard.ISchoolCardDao;
import com.xxhwap.services.schoolcard.ISchoolCardService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * book interface impl
 */
@Component("schoolCardService")
public class SchoolCardServiceImpl implements ISchoolCardService {
	@Resource(name = "schoolCardDao")
	private ISchoolCardDao schoolCardDao;
	@Override
	public long sendSchoolCard(SchoolCardBo bookInfo) {
		return schoolCardDao.sendSchoolCard(bookInfo);
	}
	public long saveSchoolCard(SchoolCardBo bookInfo) {
		return schoolCardDao.saveSchoolCard(bookInfo);
	}
	public List<SchoolCardBo> findSendSchoolCardList(Map<String,Object> queryMap) {
		return schoolCardDao.findSendSchoolCardList(queryMap);
	}

	@Override
	public SchoolCardBo findById(String id) {
		return schoolCardDao.findById(id);
	}

	@Override
	public boolean updateSchoolCard(SchoolCardBo bookInfo) {
		return schoolCardDao.updateSchoolCard(bookInfo);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return schoolCardDao.getCount(map);
	}

	@Override
	public boolean delSchoolCardById(String id) {
		return schoolCardDao.delSchoolCardById(id);
	}

	@Override
	public int findSendSchoolCardListCount(Map<String, Object> map) {
		return schoolCardDao.findSendSchoolCardListCount(map);
	}

	@Override
	public boolean updateSchoolCardForIsValid(SchoolCardBo updateSchoolCard) {
		return  schoolCardDao.updateSchoolCardForIsValid(updateSchoolCard);
	}
}
