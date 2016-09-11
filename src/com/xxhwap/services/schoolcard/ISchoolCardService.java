package com.xxhwap.services.schoolcard;

import com.xxhwap.book.SchoolCardBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ZhengYunfei on 2015/7/16
 * book interface
 */
@Service
public interface ISchoolCardService {
    public long sendSchoolCard(SchoolCardBo bookInfo) ;
    public long saveSchoolCard(SchoolCardBo bookInfo) ;
    public List<SchoolCardBo> findSendSchoolCardList(Map<String, Object> queryMap) ;
    public SchoolCardBo findById(String id);
    public boolean updateSchoolCard(SchoolCardBo bookInfo);

    public int getCount(Map<String, Object> map);

    public boolean delSchoolCardById(String id);

    public int findSendSchoolCardListCount(Map<String, Object> map);

    boolean updateSchoolCardForIsValid(SchoolCardBo updateBook);
}
