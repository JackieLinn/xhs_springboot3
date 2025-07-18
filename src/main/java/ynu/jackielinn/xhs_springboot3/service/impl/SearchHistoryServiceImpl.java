package ynu.jackielinn.xhs_springboot3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import ynu.jackielinn.xhs_springboot3.entity.po.SearchHistory;
import ynu.jackielinn.xhs_springboot3.mapper.SearchHistoryMapper;
import ynu.jackielinn.xhs_springboot3.service.SearchHistoryService;

import java.util.Date;
import java.util.List;

@Service
public class SearchHistoryServiceImpl extends ServiceImpl<SearchHistoryMapper, SearchHistory> implements SearchHistoryService {
    
    @Resource
    private SearchHistoryMapper searchHistoryMapper;

    @Override
    public void saveSearchHistory(Long uid, String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            SearchHistory history = new SearchHistory();
            history.setUid(uid);
            history.setKeyword(keyword.trim());
            history.setCreateTime(new Date());
            this.save(history);
        }
    }

    @Override
    public List<SearchHistory> getPopularKeywordsByUid(Long uid) {
        return searchHistoryMapper.getPopularKeywordsByUid(uid);
    }

    @Override
    public List<SearchHistory> getRecentSearchHistory(Long uid, int limit) {
        LambdaQueryWrapper<SearchHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SearchHistory::getUid, uid)
               .orderByDesc(SearchHistory::getCreateTime)
               .last("LIMIT " + limit);
        return this.list(wrapper);
    }
} 