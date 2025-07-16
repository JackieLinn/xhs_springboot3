package ynu.jackielinn.xhs_springboot3.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ynu.jackielinn.xhs_springboot3.entity.RestBean;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;
import ynu.jackielinn.xhs_springboot3.service.BlogService;
import ynu.jackielinn.xhs_springboot3.service.SearchHistoryService;
import ynu.jackielinn.xhs_springboot3.entity.po.SearchHistory;

import java.util.List;

@RestController
@RequestMapping("/auth/search")
public class SearchController {
    @Resource
    private BlogService blogService;

    @Resource
    private SearchHistoryService searchHistoryService;

    @GetMapping("/keyword")
    public RestBean<List<Blog>> searchBlogByKeyword(@RequestParam String keyword, @RequestParam(name = "uid") Long uid){
        // 记录搜索历史
        searchHistoryService.saveSearchHistory(uid, keyword);
        return RestBean.success(blogService.getBlogsByKeyword(keyword, uid));
    }

    @GetMapping("/history/{uid}")
    public RestBean<List<SearchHistory>> getSearchHistory(@PathVariable(name = "uid") Long uid, 
                                                         @RequestParam(name = "limit", defaultValue = "10") int limit) {
        List<SearchHistory> history = searchHistoryService.getRecentSearchHistory(uid, limit);
        return RestBean.success(history);
    }

    @GetMapping("/popular/{uid}")
    public RestBean<List<SearchHistory>> getPopularKeywords(@PathVariable(name = "uid") Long uid) {
        List<SearchHistory> popular = searchHistoryService.getPopularKeywordsByUid(uid);
        return RestBean.success(popular);
    }

}
