package com.xxhwap.services;

import com.xxhwap.book.TudouBookInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ZhengYunfei on 2015/7/16
 * book interface
 */
@Service
public interface IBookService {
    public long sendBook(TudouBookInfo bookInfo) ;
    public long saveBook(TudouBookInfo bookInfo) ;
    public List<TudouBookInfo> findSendBookList(Map<String,Object> queryMap) ;
    public TudouBookInfo findById(String id);
    public boolean updateBook(TudouBookInfo bookInfo);

    public int getCount(Map<String, Object> map);

    public boolean delBookById(String id);
}
