package com.xxhwap.dao.book;

import com.xxhwap.book.TudouBookInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhengyunfei
 *
 */
@Repository
public interface IBookDao {
	public long sendBook(TudouBookInfo bookInfo);
	public long saveBook(TudouBookInfo bookInfo);

	public List<TudouBookInfo> findSendBookList(Map<String, Object> queryMap);

	public TudouBookInfo findById(String id);

	public boolean updateBook(TudouBookInfo bookInfo);
	public int deleteByMap(Map<String, Object> queryMap);

	public int getCount(Map<String, Object> map);

	public boolean delBookById(String id);

	public int findSendBookListCount(Map<String, Object> map);

	boolean updateBookForIsValid(TudouBookInfo updateBook);

	public String findLastSelectAreaOrSchool(Map<String, Object> queryMap, String type);
}
