package ynu.jackielinn.xhs_springboot3.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ynu.jackielinn.xhs_springboot3.entity.po.History;

import java.util.List;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {
    @Select("""
    SELECT * FROM history h
    WHERE h.id IN (
        SELECT id FROM history
        WHERE uid = #{uid}
        GROUP BY h.blog_id
    )
    ORDER BY create_time DESC
""")
    List<History> getHistoryByUid(Long uid);

}
