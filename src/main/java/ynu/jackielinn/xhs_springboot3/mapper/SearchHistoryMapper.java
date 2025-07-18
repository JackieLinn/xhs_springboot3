package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.jackielinn.xhs_springboot3.entity.po.SearchHistory;

import java.util.List;

@Mapper
public interface SearchHistoryMapper extends BaseMapper<SearchHistory> {
    @Select("SELECT keyword, COUNT(*) as count FROM search_history WHERE uid = #{uid} GROUP BY keyword ORDER BY count DESC, MAX(create_time) DESC LIMIT 10")
    List<SearchHistory> getPopularKeywordsByUid(Long uid);
} 