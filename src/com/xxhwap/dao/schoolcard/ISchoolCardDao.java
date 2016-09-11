package com.xxhwap.dao.schoolcard;

import com.xxhwap.book.SchoolCardBo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhengyunfei
 *
 */
@Repository
public interface ISchoolCardDao {
	public long sendSchoolCard(SchoolCardBo bookInfo);
	public long saveSchoolCard(SchoolCardBo bookInfo);

	public List<SchoolCardBo> findSendSchoolCardList(Map<String, Object> queryMap);

	public SchoolCardBo findById(String id);

	public boolean updateSchoolCard(SchoolCardBo bookInfo);
	public int deleteByMap(Map<String, Object> queryMap);

	public int getCount(Map<String, Object> map);

	public boolean delSchoolCardById(String id);

	public int findSendSchoolCardListCount(Map<String, Object> map);

	boolean updateSchoolCardForIsValid(SchoolCardBo updateSchoolCard);
}
