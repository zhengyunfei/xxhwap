package com.xxhwap.services.impl;

import com.xxhwap.book.TudouBookInfo;
import com.xxhwap.dao.book.IBookDao;
import com.xxhwap.services.IBookService;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * book interface impl
 */
@Component("bookService")
public class BookerviceImpl implements IBookService {
	@Resource(name = "bookDao")
	private IBookDao bookDao;
	@Override
	public long sendBook(TudouBookInfo bookInfo) {
		return bookDao.sendBook(bookInfo);
	}
	public long saveBook(TudouBookInfo bookInfo) {
		return bookDao.saveBook(bookInfo);
	}
	public List<TudouBookInfo> findSendBookList(Map<String,Object> queryMap) {
		return bookDao.findSendBookList(queryMap);
	}

	@Override
	public TudouBookInfo findById(String id) {
		return bookDao.findById(id);
	}

	@Override
	public boolean updateBook(TudouBookInfo bookInfo) {
		return bookDao.updateBook(bookInfo);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return bookDao.getCount(map);
	}

	@Override
	public boolean delBookById(String id) {
		return bookDao.delBookById(id);
	}

	@Override
	public int findSendBookListCount(Map<String, Object> map) {
		return bookDao.findSendBookListCount(map);
	}
}
