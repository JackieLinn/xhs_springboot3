package ynu.jackielinn.xhs_springboot3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ynu.jackielinn.xhs_springboot3.entity.po.SearchHistory;

import java.util.List;

public interface SearchHistoryService extends IService<SearchHistory> {
    void saveSearchHistory(Long uid, String keyword);
    List<SearchHistory> getPopularKeywordsByUid(Long uid);
    List<SearchHistory> getRecentSearchHistory(Long uid, int limit);
} 