package ynu.jackielinn.xhs_springboot3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import ynu.jackielinn.xhs_springboot3.entity.po.Favorite;
import ynu.jackielinn.xhs_springboot3.entity.po.Blog;

import java.util.List;

@Mapper
public interface FavoriteMapper extends BaseMapper<Favorite> {
    @Select("SELECT COUNT(*) FROM favorite WHERE uid = #{uid} AND bid = #{bid}")
    int existsByUidAndBid(Long uid, Integer bid);

    @Delete("DELETE FROM favorite WHERE uid = #{uid} AND bid = #{bid}")
    void removeByUidAndBid(Long uid, Integer bid);

    @Select("SELECT b.* FROM blogs b LEFT JOIN favorite f ON b.id = f.bid WHERE f.uid = #{uid}")
    List<Blog> getFavoriteBlogs(Long uid);
}